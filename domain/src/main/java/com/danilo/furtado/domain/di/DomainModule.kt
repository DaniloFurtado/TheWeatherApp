package com.danilo.furtado.domain.di

import com.danilo.furtado.domain.usecase.RequestForecastUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    factory { RequestForecastUseCase(get(), get()) }
    factory { Dispatchers.IO }
}