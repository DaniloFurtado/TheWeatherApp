package com.danilo.furtado.theweatherapp.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.danilo.furtado.data.di.dataModule
import com.danilo.furtado.domain.di.domainModule
import com.danilo.furtado.theweatherapp.R
import com.danilo.furtado.theweatherapp.databinding.ActivityMainBinding
import com.danilo.furtado.theweatherapp.di.appModule
import com.danilo.furtado.theweatherapp.extensions.TODAY
import com.danilo.furtado.theweatherapp.extensions.TOMORROW
import com.danilo.furtado.theweatherapp.extensions.getDateFormattedFullDescription
import com.danilo.furtado.theweatherapp.extensions.getDateFormattedHours
import com.danilo.furtado.theweatherapp.extensions.loadImage
import com.danilo.furtado.theweatherapp.viewbindingext.viewBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class WeatherActivity : AppCompatActivity() {

    private val _binding: ActivityMainBinding by viewBinding()
    private val _viewModel: WeatherViewModel by viewModel()
    private val modules = listOf(
        domainModule,
        dataModule,
        appModule
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding.root
        loadKoinModules(modules)
        initScreen()
    }

    override fun onResume() {
        super.onResume()
        _viewModel.requestForecastWeather()
    }

    private fun initScreen() {
        lifecycleScope
            .launchWhenCreated {
                _viewModel
                    .uiState
                    .collect(::bindValues)
            }
    }

    private fun bindValues(uiState: WeatherUiState) {
        if (uiState.errorMessage?.isNotEmpty() == true) {
            handleError(uiState)
        }
        if (uiState.success.not()) return
        with(_binding) {
            progress.isVisible = uiState.isLoading
            contentLayout.isVisible = !uiState.isLoading
            textTimeLive.text = uiState.timeNow.getDateFormattedHours()
            textLocationPlace.text = uiState.localName
            textCurrentDate.text = uiState.dateDescription.getDateFormattedFullDescription()
            iconWeather.loadImage(uiState.currentConditionImg)
            iconWeatherToday.loadImage(uiState.todayImg)
            iconWeatherTomorrow.loadImage(uiState.tomorrowImg)
            iconWeatherAfterTomorrow.loadImage(uiState.dayAfterTomorrowImg)
            textTodayWeather.text = uiState.minMaxToday.replace(TODAY, getString(R.string.today))
            textTomorrowWeather.text = uiState.minMaxTomorrow.replace(TOMORROW, getString(R.string.tomorrow))
            textAfterTomorrowWeather.text = uiState.minMaxDayAfterTomorrow
            textTemperature.text = uiState.currentTemperature
            textWhatAboutDay.text = uiState.currentConditionText
            textVelocity.text = uiState.windMph
            textHumidity.text = uiState.humidity
        }
    }

    private fun handleError(uiState: WeatherUiState) {
        val snackbarError = Snackbar.make(
            _binding.contentLayout,
            uiState.errorMessage.orEmpty(),
            Snackbar.LENGTH_SHORT
        )
        snackbarError.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                _viewModel.userMessageShown()
            }
        })
        snackbarError.show()
    }

    override fun onDestroy() {
        unloadKoinModules(modules)
        super.onDestroy()
    }
}
