package com.danilo.furtado.theweatherapp.application

import android.app.Application
import com.danilo.furtado.data.di.dataModule
import com.danilo.furtado.domain.di.domainModule
import com.danilo.furtado.theweatherapp.di.appModule
import com.danilo.furtado.theweatherapp.framework.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                networkModule
            )
        }
    }
}
