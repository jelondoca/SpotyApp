package com.jelndoca.spotyapp.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {
    private val API_BASE_PATH = "https://i8rmpiaad2.execute-api.us-east-1.amazonaws.com/dev/api/"
    private var restAdapter: Retrofit? = null

    fun servicesFactory() {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        this.restAdapter = Retrofit.Builder()
            .baseUrl(API_BASE_PATH)
            .client(okHttpClient)
            .addConverterFactory(getGsonConverter())
            .build()
    }

    private fun getGsonConverter(): Converter.Factory {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd")
            .create()
        return GsonConverterFactory.create(gson)
    }

    fun getInstanceSpotyService(): SpotyService{
        servicesFactory()
        return restAdapter!!.create(SpotyService::class.java)
    }
}