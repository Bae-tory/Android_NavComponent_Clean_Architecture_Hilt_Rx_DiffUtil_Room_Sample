package com.baetory.domain.entity.book

import com.baetory.domain.entity.Entity
import java.util.*

data class BookInfo(
    val id : Int = 0,
    val authors: List<String>,
    val translators: List<String>,
    val isEnd: Boolean,
    val contents: String,
    val dateTime: Date,
    val bookNumber: String,
    val price: Int,
    val publisher: String,
    val salePrice: String,
    val saleStatus: String,
    val thumbnailImageUrl: String,
    val title: String,
    val bookDetailUrl: String
) : Entity
