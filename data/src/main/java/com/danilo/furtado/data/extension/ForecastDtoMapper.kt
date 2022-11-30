package com.danilo.furtado.data.extension

import com.danilo.furtado.data.api.dto.ConditionWeatherDto
import com.danilo.furtado.data.api.dto.CurrentWeatherDto
import com.danilo.furtado.data.api.dto.DayDto
import com.danilo.furtado.data.api.dto.ForecastContainerDto
import com.danilo.furtado.data.api.dto.ForecastDayDto
import com.danilo.furtado.data.api.dto.ForecastWeatherDto
import com.danilo.furtado.data.api.dto.LocationDto
import com.danilo.furtado.domain.model.ConditionWeather
import com.danilo.furtado.domain.model.CurrentWeather
import com.danilo.furtado.domain.model.Day
import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.model.ForecastDay
import com.danilo.furtado.domain.model.ForecastWeather
import com.danilo.furtado.domain.model.Location

fun ForecastContainerDto.fromDto() = ForecastContainer(
    location = this.location.fromDto(),
    current = this.current.fromDto(),
    forecast = this.forecast.fromDto()
)

fun LocationDto.fromDto() = Location(
    name = this.name,
    region = this.region,
    localtime = this.localtime
)

fun CurrentWeatherDto.fromDto() = CurrentWeather(
    tempCelsius = this.tempCelsius,
    tempFahrenheit = this.tempFahrenheit,
    condition = this.condition.fromDto(),
    humidity = this.humidity,
    windMph = this.windMph,
)

fun ConditionWeatherDto.fromDto() = ConditionWeather(
    conditionText = this.conditionText,
    conditionIconUrl = this.conditionIconUrl.contains("https")
        .let {
            if (it) this.conditionIconUrl else "https:${this.conditionIconUrl}"
        }
)

fun ForecastWeatherDto.fromDto() = ForecastWeather(
    forecastDay = this.forecastDay.map {
        it.fromDto()
    }
)

fun ForecastDayDto.fromDto() = ForecastDay(
    date = this.date,
    day = this.day.fromDto()
)

fun DayDto.fromDto() = Day(
    maxTempCelsius = this.maxTempCelsius,
    maxTempFahrenheit = this.maxTempFahrenheit,
    minTempCelsius = this.minTempCelsius,
    minTempFahrenheit = this.minTempFahrenheit,
    condition = this.condition.fromDto()
)
