package com.mike976.onthebigscreen.di

import com.mike976.onthebigscreen.network.IApiClient
import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.service.TmDbService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//This is like a AutoFacInstaller class for a ClassLibrary in C#
//IApiClient is resolved by Dagger in the IOC AppComponent, because it refs NetworkModule which provides IApiClient to Dagger

@Module
internal class ServiceModule {

    @Provides
    @Singleton
    fun providesTmDbService(api: IApiClient): ITmDbService {
        return TmDbService(api)
    }
}