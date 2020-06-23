package com.mike976.onthebigscreen.di

import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.network.IApiClient
import com.mike976.onthebigscreen.service.TmDbService
import com.mike976.onthebigscreen.view.paging.MediaDataSourceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PagingModule {

    @Provides
    fun providesMediaDataSourceFactory(): MediaDataSourceFactory {
        return MediaDataSourceFactory()
    }
}