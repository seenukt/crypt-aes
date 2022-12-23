package com.example.android_sdk_starter

import android.app.Application
import com.example.android_sdk_starter.di.viewModelModule
import org.koin.core.context.startKoin

class StaterSdk : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            applicationContext
            modules(listOf(viewModelModule))

        }
    }

}