package com.baetory.domain.usecase.book

import com.baetory.domain.BookRepository
import com.baetory.domain.UseCaseTest
import com.baetory.domain.exception.NoParamsException
import com.baetory.mock.MockEntity
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import kotlin.test.assertEquals

class GetCachedBooksUseCaseTest : UseCaseTest() {

    @Mock
    private lateinit var bookRepository: BookRepository
    private lateinit var getCachedBooksUseCase: GetCachedBooksUseCase

    override fun setup() {
        super.setup()
        getCachedBooksUseCase = GetCachedBooksUseCase(
            bookRepository = bookRepository,
            executorProvider = testExecutors
        )
    }

    @Test
    fun `insert 후 가져오는 것 정상 작동 여부 테스트`() {
        Mockito.`when`(
            bookRepository.getCachedBooks()
        ).thenReturn(Single.just(MockEntity.bookEntity))

        val result = getCachedBooksUseCase.execute().blockingGet()
        assertEquals(result.books.first(), MockEntity.bookEntity.books.first())
    }

    @Test
    fun `케싱 데이터 없을 때 에러 캐치 여부 테스트`() {
        val noParamsException = NoParamsException("this is no params exception")

        Mockito.`when`(
            bookRepository.getCachedBooks()
        ).thenReturn(Single.error(noParamsException))

        getCachedBooksUseCase.execute()
            .test()
            .assertError(noParamsException)
    }
}