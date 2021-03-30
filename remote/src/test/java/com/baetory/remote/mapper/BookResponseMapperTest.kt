package com.baetory.remote.mapper

import com.baetory.data.model.BookSearchDataModel
import com.baetory.remote.mapper.book.BookResponseMapper
import com.baetory.remote.mock.MockRemoteData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class BookResponseMapperTest {

    private lateinit var bookResponseMapper: BookResponseMapper

    @Before
    fun setup(){
        bookResponseMapper = BookResponseMapper()
    }

    @Test
    fun `BookResponseMapper toDataModel Test`(){
        val bookSearchResponse = MockRemoteData.bookSearchResponse
        val bookSearchDataModel : BookSearchDataModel = bookResponseMapper.toDataModel(response = bookSearchResponse)

        for(i in 0..bookSearchDataModel.bookDatas.count()){
            assertEquals(bookSearchDataModel.bookDatas[i].authors,bookSearchResponse.bookDocuments[i].authors)
            assertEquals(bookSearchDataModel.bookDatas[i].bookDetailUrl,bookSearchResponse.bookDocuments[i].bookDetailUrl)
            assertEquals(bookSearchDataModel.bookDatas[i].bookNumber,bookSearchResponse.bookDocuments[i].bookNumber)
            assertEquals(bookSearchDataModel.bookDatas[i].contents,bookSearchResponse.bookDocuments[i].contents)
            assertEquals(bookSearchDataModel.bookDatas[i].dateTime,bookSearchResponse.bookDocuments[i].dateTime)
            assertEquals(bookSearchDataModel.bookDatas[i].isEnd,bookSearchResponse.searchMetaData.isEnd)
            assertEquals(bookSearchDataModel.bookDatas[i].salePrice,bookSearchResponse.bookDocuments[i].salePrice)
            assertEquals(bookSearchDataModel.bookDatas[i].saleStatus,bookSearchResponse.bookDocuments[i].saleStatus)
            assertEquals(bookSearchDataModel.bookDatas[i].title,bookSearchResponse.bookDocuments[i].title)
            assertEquals(bookSearchDataModel.bookDatas[i].thumbnailImageUrl,bookSearchResponse.bookDocuments[i].thumbnailImageUrl)
            assertEquals(bookSearchDataModel.bookDatas[i].price,bookSearchResponse.bookDocuments[i].price)
            assertEquals(bookSearchDataModel.pagingMeta.pageableCount,bookSearchResponse.searchMetaData.pageableCount)
            assertEquals(bookSearchDataModel.pagingMeta.totalCount,bookSearchResponse.searchMetaData.totalCount)
            assertEquals(bookSearchDataModel.pagingMeta.isEnd,bookSearchResponse.searchMetaData.isEnd)
        }
    }
}