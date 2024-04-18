package com.example.mviprojectex1

import android.app.Application
import com.example.mviprojectex1.data.repo.QuoteRepoImpl
import com.example.mviprojectex1.data.source.remote.QuoteRemoteSource
import com.example.mviprojectex1.data.source.remote.QuoteRemoteSourceImpl
import com.example.mviprojectex1.network.RetrofitHelper

class MainApplication : Application() {
lateinit var quoteRepoImpl:QuoteRepoImpl

    override fun onCreate() {
        super.onCreate()
        val apiService = RetrofitHelper.getInstance("https://api.quotable.io")
        val quoteRemoteSource = QuoteRemoteSourceImpl(apiService)
        quoteRepoImpl = QuoteRepoImpl(quoteRemoteSource)
    }
}