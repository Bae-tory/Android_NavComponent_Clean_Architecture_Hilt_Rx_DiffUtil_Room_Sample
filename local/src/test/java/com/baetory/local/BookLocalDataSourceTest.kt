package com.baetory.local

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.rxjava3.EmptyResultSetException
import androidx.test.core.app.ApplicationProvider
import com.baetory.data.exception.DatabaseException
import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.local.mapper.BookLocalDataMapper
import com.baetory.local.room.LocalDataBase
import com.baetory.local.room.dao.BookDao
import com.baetory.local.source.BookLocalDataSourceImpl
import com.baetory.mock.MockLocalData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class BookLocalDataSourceTest : DataSourceTest() {

    @get: Rule
    val instanTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: LocalDataBase
    private lateinit var bookLocalMapper: BookLocalDataMapper
    private lateinit var bookLocalDataSource: BookLocalDataSource
    private val testDataModel = MockLocalData.bookDataModel

    @Mock
    private lateinit var bookDao: BookDao


    @Before
    override fun setup() {
        super.setup()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDataBase::class.java
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        bookLocalMapper = BookLocalDataMapper()
        bookLocalDataSource = BookLocalDataSourceImpl(database.bookDao(), bookLocalMapper)
    }

    @After
    override fun tearDown() {
        database.close()
    }

    @Test
    fun `Insert 동작 테스트`() {
        bookLocalDataSource.insert(dataModel = testDataModel).test().assertComplete()

        // drop
        bookLocalDataSource.drop().test().assertComplete()

        val dataModels = MockLocalData.bookDataModels
        bookLocalDataSource.insert(dataModels = dataModels).test().assertComplete()
        bookLocalDataSource.hasItem(id = dataModels.first().id).test().assertValue(true)
        bookLocalDataSource.selectAllCount().test().assertValue(dataModels.count())

        bookLocalDataSource.drop()
    }

    @Test
    fun `Update 동작 테스트`() {
        bookLocalDataSource.insert(dataModel = testDataModel).test().assertComplete()
        bookLocalDataSource.selectAllCount().test().assertValue(1)

        // After Update
        val copiedDataModel = testDataModel.copy(contents = "수정된 데이터 모델")
        bookLocalDataSource.update(dataModel = copiedDataModel).test().assertComplete()

        val fetchedDataModel = bookLocalDataSource.selectAll().blockingGet().first()
        assertEquals(expected = copiedDataModel.bookNumber, actual = fetchedDataModel.bookNumber)
        assertEquals(expected = copiedDataModel.contents, actual = fetchedDataModel.contents)
        assertEquals(expected = copiedDataModel.isEnd, actual = fetchedDataModel.isEnd)
        assertEquals(expected = copiedDataModel.publisher, actual = fetchedDataModel.publisher)
        assertEquals(expected = copiedDataModel.saleStatus, actual = fetchedDataModel.saleStatus)
        assertEquals(expected = copiedDataModel.dateTime, actual = fetchedDataModel.dateTime)

        bookLocalDataSource.drop()
    }

    @Test
    fun `Delete 동작 테스트`() {
        bookLocalDataSource.insert(dataModel = testDataModel).test().assertComplete()
        bookLocalDataSource.selectAllCount().test().assertValue(1)

        // After Delete
        bookLocalDataSource.deleteById(id = testDataModel.id).test().assertComplete()
        bookLocalDataSource.selectAllCount().test().assertValue(0)
        bookLocalDataSource.hasItem(id = testDataModel.id).test().assertValue(false)
        bookLocalDataSource.selectAllCount().test().assertValue(0)


        // After Drop
        bookLocalDataSource.insert(dataModel = testDataModel).test().assertComplete()
        bookLocalDataSource.selectAllCount().test().assertValue(1)
        bookLocalDataSource.drop().test().assertComplete()
        bookLocalDataSource.hasItem(id = testDataModel.id).test().assertValue(false)
        bookLocalDataSource.selectAllCount().test().assertValue(0)

        bookLocalDataSource.drop()
    }


    @Test
    fun `BookDao hasItem 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when hasItem")
        val mockedBookLocalDataSource = BookLocalDataSourceImpl(bookDao, bookLocalMapper)

        // When
        Mockito.`when`(bookDao.selectAllCount()).thenReturn(Single.error(exception))

        mockedBookLocalDataSource.selectAllCount()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)


        bookLocalDataSource.drop()
    }

    @Test
    fun `BookDao selectAll 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when selectAll")
        val mockedBookLocalDataSource = BookLocalDataSourceImpl(bookDao, bookLocalMapper)

        // When
        Mockito.`when`(bookDao.selectAllCount()).thenReturn(Single.error(exception))

        // Then
        mockedBookLocalDataSource.selectAllCount()
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)

        bookLocalDataSource.drop()
    }

    @Test
    fun `BookDao update 동작 중 EmptyResultSetException 에러 발생 시 캐치 여부 테스트`() {
        val exception = EmptyResultSetException("Triggering EmptyResultSetException when update")
        val mappedRoomObject = bookLocalMapper.toRoomObject(dataModel = testDataModel)
        val mockedBookLocalDataSource = BookLocalDataSourceImpl(bookDao, bookLocalMapper)

        // When
        Mockito.`when`(bookDao.update(roomObject = mappedRoomObject)).thenReturn(Completable.error(exception))


        // Then
        bookLocalDataSource.update(dataModel = testDataModel).test().assertComplete() // 정상 케이스
        bookLocalDataSource.update(dataModel = testDataModel) // 비정상 케이스
            .test()
            .assertError(DatabaseException.NotFoundException::class.java)

        bookLocalDataSource.drop()
    }
}