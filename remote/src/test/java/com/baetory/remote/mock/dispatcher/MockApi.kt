package com.baetory.remote.mock.dispatcher

import com.baetory.remote.api.BookApi
import com.baetory.remote.mock.okHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

internal fun mockBookApi(mockWebServer: MockWebServer): BookApi =
    Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(BookApi::class.java)