package com.danilo.furtado.theweatherapp.presentation.home

import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.usecase.RequestForecastUseCase
import com.danilo.furtado.domain.usecase.requests.model.ForecastRequest
import com.danilo.furtado.theweatherapp.base.BaseViewModel
import com.danilo.furtado.theweatherapp.extensions.getDescDayOfDat
import com.danilo.furtado.theweatherapp.framework.network.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherViewModel(
    private val requestForecastUseCase: RequestForecastUseCase
) : BaseViewModel(requestForecastUseCase) {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun requestForecastWeather() {
        _uiState.update { it.copy(success = true) }
        requestForecastUseCase
            .execute(
                params = ForecastRequest(API_KEY, "New York", QUANTITY_DAYS_FORECAST),
                onResult = ::handleResult,
                onError = { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = throwable.message, success = false)
                    }
                },
                onStartLoad = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFinishLoad = {
                    _uiState.update { it.copy(isLoading = false) }
                }
            )
    }

    private fun handleResult(forecastContainer: ForecastContainer) {
        WeatherUiState(
            success = true,
            timeNow = forecastContainer.location.localtime,
            localName = forecastContainer.location.name,
            dateDescription = forecastContainer.location.localtime,
            currentConditionImg = forecastContainer.current.condition.conditionIconUrl,
            currentTemperature = "${forecastContainer.current.tempFahrenheit} °F",
            currentConditionText = forecastContainer.current.condition.conditionText,
            windMph = "${forecastContainer.current.windMph} mph",
            humidity = "${forecastContainer.current.humidity} %",
            minMaxToday = returnMinMaxOfDay(forecastContainer, TODAY_INDEX),
            todayImg = returnConditionToday(forecastContainer)?.conditionIconUrl.orEmpty(),
            minMaxTomorrow = returnMinMaxOfDay(forecastContainer, TOMORROW_INDEX),
            tomorrowImg = returnConditionTomorrow(forecastContainer)?.conditionIconUrl.orEmpty(),
            minMaxDayAfterTomorrow = returnMinMaxOfDay(forecastContainer, AFTER_TOMORROW_INDEX),
            dayAfterTomorrowImg = returnConditionAfterTomorrow(forecastContainer)?.conditionIconUrl.orEmpty(),
            isLoading = false,
            errorMessage = ""
        ).let { _uiState.value = it }
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(errorMessage = null)
        }
    }

    private fun returnConditionToday(forecastContainer: ForecastContainer) =
        returnCondition(forecastContainer, TODAY_INDEX)?.day?.condition

    private fun returnConditionTomorrow(forecastContainer: ForecastContainer) =
        returnCondition(forecastContainer, TOMORROW_INDEX)?.day?.condition

    private fun returnConditionAfterTomorrow(forecastContainer: ForecastContainer) =
        returnCondition(forecastContainer, AFTER_TOMORROW_INDEX)?.day?.condition

    private fun returnCondition(forecastContainer: ForecastContainer, index: Int) =
        forecastContainer.forecast.forecastDay.getOrNull(index)

    private fun returnMinMaxOfDay(forecastContainer: ForecastContainer, index: Int): String {
        val forecastDay = returnCondition(forecastContainer, index)
        val day = returnCondition(forecastContainer, index)?.day
        return "${
        day?.minTempFahrenheit
        }/${
        day?.maxTempFahrenheit
        }F\n${
        forecastDay?.date?.getDescDayOfDat()
        }"
    }

    companion object {

        private const val QUANTITY_DAYS_FORECAST = 3
        private const val TODAY_INDEX = 0
        private const val TOMORROW_INDEX = 1
        private const val AFTER_TOMORROW_INDEX = 2
    }
}
