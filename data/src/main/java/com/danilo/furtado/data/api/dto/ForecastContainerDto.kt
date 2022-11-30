package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName

data class ForecastContainerDto(
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("current")
    val current: CurrentWeatherDto,
    @SerializedName("forecast")
    val forecast: ForecastWeatherDto
)
