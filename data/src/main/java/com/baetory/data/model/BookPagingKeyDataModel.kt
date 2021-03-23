package com.baetory.data.model

import com.baetory.data.mapper.DataModel
import java.util.*

data class BookPagingKeyDataModel(
    val id : Int = 0,
    val prevKey : Int?,
    val nextKey : Int?
) : DataModel
