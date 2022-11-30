package com.danilo.furtado.domain.usecase

import com.danilo.furtado.domain.base.MainUseCase
import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.repository.ForecastRepository
import com.danilo.furtado.domain.usecase.requests.model.ForecastRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import java.security.InvalidParameterException

class RequestForecastUseCase(
    dispatcher: CoroutineDispatcher,
    private val forecastRepository: ForecastRepository
) : MainUseCase<ForecastContainer, ForecastRequest>(dispatcher) {

    override suspend fun buildUseCase(params: ForecastRequest?): Flow<ForecastContainer> {
        return if (params != null) {
            forecastRepository
                .requestWeatherForecast(
                    params.apiKey,
                    params.location,
                    params.quantityDays
                )
        } else {
            throw InvalidParameterException("Invalid parameter")
        }
    }
}
