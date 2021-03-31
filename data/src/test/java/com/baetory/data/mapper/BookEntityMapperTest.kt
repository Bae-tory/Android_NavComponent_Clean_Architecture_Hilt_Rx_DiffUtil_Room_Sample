package com.baetory.data.mapper

import com.baetory.data.mapper.book.BookEntityMapper
import com.baetory.data.mapper.book.BookInfoEntityMapper
import com.baetory.data.mock.mockdata.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookEntityMapperTest {

    private lateinit var bookEntityMapper: BookEntityMapper
    private lateinit var bookInfoEntityMapper: BookInfoEntityMapper

    @Before
    fun setup() {
        bookInfoEntityMapper = BookInfoEntityMapper()
        bookEntityMapper = BookEntityMapper(bookInfoEntityMapper = bookInfoEntityMapper)
    }

    @Test
    fun `BookEntityMapper toEntity 동작 테스트`() {
        val bookDataModel = MockData.bookSearchDataModel
        val bookEntity = bookEntityMapper.toEntity(bookDataModel)
        for (i in 0..bookEntity.books.count()) {
            assertEquals(bookEntity.books[i].id, bookDataModel.bookDatas[i].id)
            assertEquals(bookEntity.books[i].authors, bookDataModel.bookDatas[i].authors)
            assertEquals(
                bookEntity.books[i].bookDetailUrl,
                bookDataModel.bookDatas[i].bookDetailUrl
            )
            assertEquals(bookEntity.books[i].bookNumber, bookDataModel.bookDatas[i].bookNumber)
            assertEquals(bookEntity.books[i].contents, bookDataModel.bookDatas[i].contents)
            assertEquals(bookEntity.books[i].dateTime, bookDataModel.bookDatas[i].dateTime)
            assertEquals(bookEntity.books[i].publisher, bookDataModel.bookDatas[i].publisher)
            assertEquals(bookEntity.books[i].salePrice, bookDataModel.bookDatas[i].salePrice)
            assertEquals(bookEntity.books[i].saleStatus, bookDataModel.bookDatas[i].saleStatus)
            assertEquals(bookEntity.books[i].translators, bookDataModel.bookDatas[i].translators)
            assertEquals(bookEntity.books[i].isEnd, bookDataModel.bookDatas[i].isEnd)
            assertEquals(
                bookEntity.books[i].thumbnailImageUrl,
                bookDataModel.bookDatas[i].thumbnailImageUrl
            )
            assertEquals(bookEntity.books[i].title, bookDataModel.bookDatas[i].title)
        }
    }

    @Test
    fun `BookEntityMapper toDataModel 동작 테스트`() {
        val bookEntity = MockData.bookEntity
        val bookDataModel = bookEntityMapper.toDataModel(bookEntity)
        for (i in 0..bookDataModel.bookDatas.count()) {
            assertEquals(bookEntity.books[i].id, bookDataModel.bookDatas[i].id)
            assertEquals(bookEntity.books[i].authors, bookDataModel.bookDatas[i].authors)
            assertEquals(
                bookEntity.books[i].bookDetailUrl,
                bookDataModel.bookDatas[i].bookDetailUrl
            )
            assertEquals(bookEntity.books[i].bookNumber, bookDataModel.bookDatas[i].bookNumber)
            assertEquals(bookEntity.books[i].contents, bookDataModel.bookDatas[i].contents)
            assertEquals(bookEntity.books[i].dateTime, bookDataModel.bookDatas[i].dateTime)
            assertEquals(bookEntity.books[i].publisher, bookDataModel.bookDatas[i].publisher)
            assertEquals(bookEntity.books[i].salePrice, bookDataModel.bookDatas[i].salePrice)
            assertEquals(bookEntity.books[i].saleStatus, bookDataModel.bookDatas[i].saleStatus)
            assertEquals(bookEntity.books[i].translators, bookDataModel.bookDatas[i].translators)
            assertEquals(bookEntity.books[i].isEnd, bookDataModel.bookDatas[i].isEnd)
            assertEquals(
                bookEntity.books[i].thumbnailImageUrl,
                bookDataModel.bookDatas[i].thumbnailImageUrl
            )
            assertEquals(bookEntity.books[i].title, bookDataModel.bookDatas[i].title)
        }
    }
}