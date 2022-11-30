package com.danilo.furtado.data.api.remote

import com.danilo.furtado.data.api.dto.ForecastContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(FORECAST)
    suspend fun getForecastWeather(
        @Query("key") key: String,
        @Query("q") locationRequest: String,
        @Query("days") days: Int,
    ): ForecastContainerDto

    companion object {
        private const val FORECAST = "forecast.json"
    }
}
