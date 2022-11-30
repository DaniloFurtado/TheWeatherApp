package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTempCelsius: Float,
    @SerializedName("maxtemp_f")
    val maxTempFahrenheit: Float,
    @SerializedName("mintemp_c")
    val minTempCelsius: Float,
    @SerializedName("mintemp_f")
    val minTempFahrenheit: Float,
    @SerializedName("condition")
    val condition: ConditionWeatherDto
)