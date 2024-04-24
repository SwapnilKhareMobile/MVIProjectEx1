package com.example.mviprojectex1.data.repo

import com.example.mviprojectex1.data.source.remote.QuoteRemoteSource
import com.example.mviprojectex1.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepoImpl @Inject constructor(private val quoteRemoteSource: QuoteRemoteSource) :
    QuoteRepo {
    override fun getQuoteList(pageId: String): Flow<List<Result>?> =
        quoteRemoteSource.getRemoteQuote(pageId)
            .map {
                it?.results
            }
}