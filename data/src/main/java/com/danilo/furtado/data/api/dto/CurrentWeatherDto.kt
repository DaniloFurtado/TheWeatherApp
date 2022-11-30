package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("temp_c")
    val tempCelsius: Float,
    @SerializedName("temp_f")
    val tempFahrenheit: Float,
    @SerializedName("condition")
    val condition: ConditionWeatherDto,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("wind_mph")
    val windMph: Float,
)
