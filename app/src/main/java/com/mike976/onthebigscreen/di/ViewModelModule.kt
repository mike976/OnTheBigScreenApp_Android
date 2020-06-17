package com.mike976.onthebigscreen.di

import com.example.onthebigscreen.service.ITmDbService
import com.mike976.onthebigscreen.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesMainViewModelFactory(service: ITmDbService): MainViewModelFactory {
        return MainViewModelFactory(service)
    }

}