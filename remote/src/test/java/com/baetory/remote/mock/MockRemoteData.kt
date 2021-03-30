package com.baetory.remote.mock

import com.baetory.remote.model.book.BookResponse
import com.baetory.remote.model.book.BookResponseMetaData
import com.baetory.remote.model.book.BookSearchResponse
import java.util.*

internal object MockRemoteData {

    val bookResponse = BookResponse(
        authors = listOf("작가1", "작가2"),
        contents = "컨텐츠입니다.",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 1000,
        publisher = "출판사",
        salePrice = "1000",
        saleStatus = "판매상태",
        thumbnailImageUrl = "이미지url",
        title = "제목",
        bookTranslators = listOf("번역가1", "번역가2"),
        bookDetailUrl = "상세이미지url"
    )

    val bookResponseMetaData = BookResponseMetaData(
        isEnd = true,
        pageableCount = 0,
        totalCount = 1
    )

    val bookResponses = listOf(bookResponse, bookResponse)

    val bookSearchResponse = BookSearchResponse(
        bookDocuments = bookResponses,
        searchMetaData = bookResponseMetaData
    )

}