package com.danilo.furtado.theweatherapp.presentation.home

import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.usecase.RequestForecastUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    private val captorResult = slot<(ForecastContainer) -> Unit>()
    private val captorError = slot<(Throwable) -> Unit>()
    private var requestForecastUseCase = mockk<RequestForecastUseCase>()
    private val forecastContainerData = dummyForecastContainer()
    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun beforeStart() {
        weatherViewModel = WeatherViewModel(requestForecastUseCase)
    }

    @Test
    fun `request with success`() = runTest {
        `given request with success`()
        `when request forecast with success`()
        `then request forecast with success`()
    }

    private fun `given request with success`() {
        every {
            requestForecastUseCase(any(), capture(captorResult), any(), any(), any())
        } answers {
            captorResult.captured.invoke(forecastContainerData)
        }
    }

    private fun `when request forecast with success`() {
        weatherViewModel.requestForecastWeather()
    }

    private fun `then request forecast with success`() {
        val result = weatherViewModel.uiState.value
        assertEquals(true, result.success)
        assertEquals(forecastContainerData.location.name, result.localName)
        assertEquals("${forecastContainerData.current.humidity} %", result.humidity)

        verify(exactly = 1) {
            requestForecastUseCase(any(), any(), any(), any(), any())
        }
    }

    @Test
    fun `request error handle properly with`() = runTest {
        `given request error handle properly with`()
        `when request error handle properly with`()
        `then request error handle properly with`()
    }

    private fun `given request error handle properly with`() {
        coEvery {
            requestForecastUseCase(any(), any(), capture(captorError), any(), any())
        } answers {
            captorError.captured.invoke(Exception("Error test"))
        }
    }

    private fun `when request error handle properly with`() {
        weatherViewModel.requestForecastWeather()
    }

    private fun `then request error handle properly with`() {
        val result = weatherViewModel.uiState.value
        assertEquals(false, result.success)
        assertEquals("Error test", result.errorMessage)

        verify(exactly = 1) {
            requestForecastUseCase(any(), any(), any(), any(), any())
        }
    }

    @Test
    fun `start view model properly`() = runTest {
        `given start view model properly`()
        `when start view model properly`()
        `then start view model properly`()
    }

    private fun `given start view model properly`() {
        coEvery {
            requestForecastUseCase(any(), capture(captorResult), any(), any(), any())
        } answers {
            captorResult.captured.invoke(forecastContainerData)
        }
    }

    private fun `when start view model properly`() {
        weatherViewModel.requestForecastWeather()
    }

    private fun `then start view model properly`() {
        verify(exactly = 1) {
            requestForecastUseCase(any(), any(), any(), any(), any())
        }
    }
}
