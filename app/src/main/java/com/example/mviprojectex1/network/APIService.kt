package com.example.mviprojectex1.network

import com.example.mviprojectex1.model.QuoteAppResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/quotes")
    suspend fun getQuoteList(@Query("page") pageId: String): Response<QuoteAppResponse>
}