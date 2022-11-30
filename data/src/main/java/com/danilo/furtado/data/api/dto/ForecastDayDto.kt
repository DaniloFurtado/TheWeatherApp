package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ForecastDayDto(
    @SerializedName("date")
    val date: Date,
    @SerializedName("day")
    val day: DayDto
)