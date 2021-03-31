package com.baetory.data.mock.remote

import com.baetory.data.mock.mockdata.MockData
import com.baetory.data.model.BookSearchDataModel
import com.baetory.data.source.remote.BookRemoteDataSource
import io.reactivex.rxjava3.core.Single

class MockBookRemoteDataSourceTest : BookRemoteDataSource {

    private val bookSearchDataModel = MockData.bookSearchDataModel

    override fun getBooks(
        query: String,
        sort: String,
        page: Int,
        target: String
    ): Single<BookSearchDataModel> =
        Single.just(bookSearchDataModel)
}