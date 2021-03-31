package com.baetory.data.mapper

import com.baetory.data.mapper.book.BookInfoEntityMapper
import com.baetory.data.mock.mockdata.MockData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookInfoEntityMapperTest {

    private lateinit var bookInfoEntityMapper: BookInfoEntityMapper

    @Before
    fun setup() {
        bookInfoEntityMapper = BookInfoEntityMapper()
    }

    @Test
    fun `BookInfoEntityMapper toEntity 동작 테스트`() {
        val bookDataModel = MockData.bookDataModel
        val bookInfo = bookInfoEntityMapper.toEntity(bookDataModel)
        assertEquals(bookDataModel.id, bookInfo.id)
        assertEquals(bookDataModel.price, bookInfo.price)
        assertEquals(bookDataModel.thumbnailImageUrl, bookInfo.thumbnailImageUrl)
        assertEquals(bookDataModel.title, bookInfo.title)
        assertEquals(bookDataModel.saleStatus, bookInfo.saleStatus)
        assertEquals(bookDataModel.isEnd, bookInfo.isEnd)
        assertEquals(bookDataModel.dateTime, bookInfo.dateTime)
        assertEquals(bookDataModel.bookNumber, bookInfo.bookNumber)
        assertEquals(bookDataModel.translators, bookInfo.translators)
        assertEquals(bookDataModel.salePrice, bookInfo.salePrice)
        assertEquals(bookDataModel.bookDetailUrl, bookInfo.bookDetailUrl)
        assertEquals(bookDataModel.publisher, bookInfo.publisher)
    }

    @Test
    fun `BookInfoEntityMapper toDataModel 동작 테스트`() {
        val bookInfo = MockData.bookInfo
        val bookDataModel = bookInfoEntityMapper.toDataModel(bookInfo)
        assertEquals(bookDataModel.id, bookInfo.id)
        assertEquals(bookDataModel.price, bookInfo.price)
        assertEquals(bookDataModel.thumbnailImageUrl, bookInfo.thumbnailImageUrl)
        assertEquals(bookDataModel.title, bookInfo.title)
        assertEquals(bookDataModel.saleStatus, bookInfo.saleStatus)
        assertEquals(bookDataModel.isEnd, bookInfo.isEnd)
        assertEquals(bookDataModel.dateTime, bookInfo.dateTime)
        assertEquals(bookDataModel.bookNumber, bookInfo.bookNumber)
        assertEquals(bookDataModel.translators, bookInfo.translators)
        assertEquals(bookDataModel.salePrice, bookInfo.salePrice)
        assertEquals(bookDataModel.bookDetailUrl, bookInfo.bookDetailUrl)
        assertEquals(bookDataModel.publisher, bookInfo.publisher)
    }

}