package com.example.mviprojectex1.data.source.remote

import com.example.mviprojectex1.model.QuoteAppResponse
import com.example.mviprojectex1.network.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class QuoteRemoteSourceImpl @Inject constructor(private val quoteAPI: APIService) : QuoteRemoteSource {
    override  fun getRemoteQuote(pageId: String): Flow<QuoteAppResponse?> = flow {
        emit(quoteAPI.getQuoteList(pageId).body())
    }.flowOn(Dispatchers.IO)
}