package com.mike976.onthebigscreen.di

import com.mike976.onthebigscreen.network.IApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASEURL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): IApiClient {
        return retrofit.create(IApiClient::class.java)
    }
}