package com.baetory.mock

import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.entity.book.BookInfo
import com.baetory.domain.entity.book.PageMetaData
import java.util.*

object MockEntity {

    val pageMetaData = PageMetaData(
        isEnd = true,
        pageableCount = 1,
        totalCount = 1
    )

    val bookInfo = BookInfo(
        id = 0,
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        isEnd = true,
        contents = "컨텐츠",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 1000,
        publisher = "출판사",
        salePrice = "1000",
        saleStatus = "1500",
        thumbnailImageUrl = "이미지URL",
        title = "제목",
        bookDetailUrl = "상세이미지URL"
    )

    val bookInfos = listOf(bookInfo, bookInfo)

    val bookEntity = BookEntity(
        books = bookInfos,
        pageMeta = pageMetaData
    )
}