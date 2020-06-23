package com.mike976.onthebigscreen.app

import android.app.Application
import com.mike976.onthebigscreen.di.AppComponent
import com.mike976.onthebigscreen.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .build()

    }
}

//we expose the Dagger Component IOC container for the lifetime of the app
//notice in AndroidManifest.xml we have added name to the application element woth .app.App

lateinit var component: AppComponent