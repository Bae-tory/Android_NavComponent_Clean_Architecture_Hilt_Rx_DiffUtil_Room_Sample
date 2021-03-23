package com.baetory.data.model

import com.baetory.data.mapper.DataModel
import java.util.*

data class BookDataModel(
    val id : Int,
    val isEnd: Boolean,
    val authors: List<String>,
    val translators: List<String>,
    val contents: String,
    val dateTime: Date,
    val bookNumber: String,
    val price: Int,
    val publisher: String,
    val salePrice: String,
    val saleStatus: String, // 단순 노출 용도로만 쓸 것
    val thumbnailImageUrl: String,
    val title: String,
    val bookDetailUrl: String
) : DataModel

data class BookPagingMetaDataModel(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int
) : DataModel
