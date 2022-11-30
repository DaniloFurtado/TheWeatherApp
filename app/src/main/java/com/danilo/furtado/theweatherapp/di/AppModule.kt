package com.danilo.furtado.theweatherapp.di

import com.danilo.furtado.theweatherapp.presentation.home.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { WeatherViewModel(get()) }
}
