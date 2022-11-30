package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName

data class ForecastWeatherDto(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayDto>
)
