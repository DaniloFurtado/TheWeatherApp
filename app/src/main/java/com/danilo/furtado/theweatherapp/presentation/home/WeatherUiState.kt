package com.danilo.furtado.theweatherapp.presentation.home

import java.util.Date

data class WeatherUiState(
    val success: Boolean = false,
    val timeNow: Date? = null,
    val localName: String = "",
    val dateDescription: Date? = null,
    val currentConditionImg: String = "",
    val currentTemperature: String = "",
    val currentConditionText: String = "",
    val windMph: String = "",
    val humidity: String = "",
    val minMaxToday: String = "",
    val todayImg: String = "",
    val minMaxTomorrow: String = "",
    val tomorrowImg: String = "",
    val minMaxDayAfterTomorrow: String = "",
    val dayAfterTomorrowImg: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)

sealed class UiState {
    class Success(val desc: String) : UiState()
    object Loading : UiState()
}
