package com.example.mviprojectex1.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object  {
        fun getInstance(baseUrl:String):APIService{
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(networkInterceptor())
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .baseUrl(baseUrl)
                .build()
                .create(APIService::class.java)
        }

        private fun networkInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return loggingInterceptor
        }
    }
}