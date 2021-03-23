package com.baetory.mock

import com.baetory.data.model.BookDataModel
import com.baetory.local.room.BookRoomObject
import java.util.*

object MockLocalData {

    val bookRoomObject1 = BookRoomObject(
        id = 1,
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        isEnd = false,
        contents = "컨텐츠입니다.",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 100,
        publisher = "출판사",
        salePrice = "100",
        saleStatus = "판매중",
        bookImageUrl = "imageUrl1",
        title = "제목",
        bookDetailUrl = "detailImageUrl"
    )

    val bookRoomObject2 = BookRoomObject(
        id = 2,
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        isEnd = false,
        contents = "컨텐츠입니다.",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 100,
        publisher = "출판사",
        salePrice = "100",
        saleStatus = "판매중",
        bookImageUrl = "imageUrl1",
        title = "제목",
        bookDetailUrl = "detailImageUrl"
    )

    val bookDataModel = BookDataModel(
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        isEnd = false,
        contents = "컨텐츠입니다.",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 100,
        publisher = "출판사",
        salePrice = "100",
        saleStatus = "판매중",
        thumbnailImageUrl = "imageUrl1",
        title = "제목",
        bookDetailUrl = "detailImageUrl"
    )

    val bookRoomObjects = listOf(bookRoomObject1, bookRoomObject2)
    val bookDataModels = listOf(bookDataModel, bookDataModel)

}
/*    data class BookRoomObject(
        @PrimaryKey(autoGenerate = true)                     var id:                      Long?               = 1L,
        @ColumnInfo(name = "authors")                        val authors:                 List<String>,
        @ColumnInfo(name = "translators")                    val translators:             List<String>,
        @ColumnInfo(name = "is_end")                         val isEnd:                   Boolean,
        @ColumnInfo(name = "contents")                       val contents:                String,
        @ColumnInfo(name = "date_time")                      val dateTime:                Date,
        @ColumnInfo(name = "book_number")                    val bookNumber:              String,
        @ColumnInfo(name = "price")                          val price:                   Int,
        @ColumnInfo(name = "publisher")                      val publisher:               String,
        @ColumnInfo(name = "sale_price")                     val salePrice:               String,
        @ColumnInfo(name = "sale_status")                    val saleStatus:              String,
        @ColumnInfo(name = "book_image_url")                 val bookImageUrl:            String,
        @ColumnInfo(name = "title")                          val title:                   String,
        @ColumnInfo(name = "book_detail_url")                val bookDetailUrl:           String*/