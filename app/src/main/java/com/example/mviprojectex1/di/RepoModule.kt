package com.example.mviprojectex1.di

import com.example.mviprojectex1.data.repo.QuoteRepo
import com.example.mviprojectex1.data.repo.QuoteRepoImpl
import com.example.mviprojectex1.data.source.remote.QuoteRemoteSource
import com.example.mviprojectex1.data.source.remote.QuoteRemoteSourceImpl
import com.example.mviprojectex1.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideQuoteRepo(quoteRemoteSource: QuoteRemoteSource):QuoteRepo{
        return QuoteRepoImpl(quoteRemoteSource)
    }

    @Provides
    fun provideQuoteRemoteSource(apiService: APIService):QuoteRemoteSource{
        return QuoteRemoteSourceImpl(apiService)
    }
}