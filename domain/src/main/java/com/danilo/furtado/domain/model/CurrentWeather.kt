package com.danilo.furtado.domain.model

data class CurrentWeather(
    val tempCelsius: Float,
    val tempFahrenheit: Float,
    val condition: ConditionWeather,
    val humidity: Int,
    val windMph: Float,
)
