package com.danilo.furtado.theweatherapp.presentation.home

import com.danilo.furtado.domain.model.ConditionWeather
import com.danilo.furtado.domain.model.CurrentWeather
import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.model.ForecastWeather
import com.danilo.furtado.domain.model.Location
import java.util.Date

fun dummyForecastContainer() =
    ForecastContainer(
        Location(
            "New York",
            "State of New York",
            Date()
        ),
        CurrentWeather(
            87.9f,
            40.2f,
            ConditionWeather(
                "Sunny",
                "https://icon.url.fake"
            ),
            40,
            15f
        ),
        ForecastWeather(
            listOf()
        )
    )
