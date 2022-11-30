package com.danilo.furtado.data.di

import com.danilo.furtado.data.api.remote.WeatherApi
import com.danilo.furtado.data.api.repository.ForecastRepositoryImp
import com.danilo.furtado.domain.repository.ForecastRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<ForecastRepository> { ForecastRepositoryImp(get()) }
    factory {
        get<Retrofit>()
            .create(WeatherApi::class.java)
    }
}
