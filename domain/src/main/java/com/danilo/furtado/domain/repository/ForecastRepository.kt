package com.danilo.furtado.domain.repository

import com.danilo.furtado.domain.model.ForecastContainer
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    fun requestWeatherForecast(
        key: String,
        locationRequest: String,
        days: Int
    ): Flow<ForecastContainer>
}
