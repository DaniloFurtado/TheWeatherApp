package com.danilo.furtado.domain.usecase.requests.model

data class ForecastRequest(
    val apiKey: String,
    val location: String,
    val quantityDays: Int
)
