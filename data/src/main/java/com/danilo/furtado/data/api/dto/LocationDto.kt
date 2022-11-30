package com.danilo.furtado.data.api.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LocationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("localtime")
    val localtime: Date
)
