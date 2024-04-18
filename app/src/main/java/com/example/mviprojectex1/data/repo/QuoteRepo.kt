package com.example.mviprojectex1.data.repo

import com.example.mviprojectex1.model.QuoteAppResponse
import com.example.mviprojectex1.model.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface QuoteRepo {

    fun getQuoteList(pageId:String): Flow<List<Result>?>
}