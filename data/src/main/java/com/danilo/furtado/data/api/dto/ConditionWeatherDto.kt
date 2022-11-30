package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName

data class ConditionWeatherDto(
    @SerializedName("text")
    val conditionText: String,
    @SerializedName("icon")
    val conditionIconUrl: String,
)