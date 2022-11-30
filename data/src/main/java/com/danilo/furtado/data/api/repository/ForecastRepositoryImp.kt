package com.danilo.furtado.data.api.repository

import com.danilo.furtado.data.api.remote.WeatherApi
import com.danilo.furtado.data.extension.fromDto
import com.danilo.furtado.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.flow

class ForecastRepositoryImp(
    private val weatherApi: WeatherApi
) : ForecastRepository {

    override fun requestWeatherForecast(
        key: String,
        locationRequest: String,
        days: Int
    ) = flow {
        weatherApi
            .getForecastWeather(key, locationRequest, days)
            .fromDto()
            .let { emit(it) }
    }
}
