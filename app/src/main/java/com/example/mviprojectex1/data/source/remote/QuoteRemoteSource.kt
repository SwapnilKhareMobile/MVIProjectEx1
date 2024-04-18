package com.example.mviprojectex1.data.source.remote

import com.example.mviprojectex1.model.QuoteAppResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface QuoteRemoteSource {
     fun getRemoteQuote(pageId: String): Flow<QuoteAppResponse?>
}