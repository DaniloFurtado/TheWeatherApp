package com.danilo.furtado.domain.model

data class Day(
    val maxTempCelsius: Float,
    val maxTempFahrenheit: Float,
    val minTempCelsius: Float,
    val minTempFahrenheit: Float,
    val condition: ConditionWeather
)
