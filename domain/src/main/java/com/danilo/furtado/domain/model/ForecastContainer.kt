package com.danilo.furtado.domain.model

data class ForecastContainer(
    val location: Location,
    val current: CurrentWeather,
    val forecast: ForecastWeather
)
