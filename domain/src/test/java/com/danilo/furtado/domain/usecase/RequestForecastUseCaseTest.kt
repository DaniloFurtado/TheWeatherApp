package com.danilo.furtado.domain.usecase

import com.danilo.furtado.domain.MainDispatcherRule
import com.danilo.furtado.domain.model.ForecastContainer
import com.danilo.furtado.domain.repository.ForecastRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import java.security.InvalidParameterException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RequestForecastUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private var repository = mockk<ForecastRepository>()
    private lateinit var userCase: RequestForecastUseCase
    private val forecastContainer = mockk<ForecastContainer>(relaxed = true)

    @Before
    fun `before test`() {
        userCase = RequestForecastUseCase(
            mainDispatcherRule.testDispatcher,
            repository
        )
    }

    @Test
    fun `request with success`() = runTest {
        `given request with success`()
        `then request with success`()
    }

    private fun `given request with success`() {
        coEvery {
            repository.requestWeatherForecast(any(), any(), any())
        } returns flowOf(forecastContainer)
    }

    private suspend fun `then request with success`() {
        val result = userCase.buildUseCase(mockk(relaxed = true)).single()
        assertEquals(forecastContainer, result)
    }

    @Test(expected = InvalidParameterException::class)
    fun `request with invalid parameter`() = runTest {
        `given with invalid parameter`()
        `then with invalid parameter`()
    }

    private fun `given with invalid parameter`() {
        coEvery {
            repository.requestWeatherForecast(any(), any(), any())
        } returns flowOf(forecastContainer)
    }

    private suspend fun `then with invalid parameter`() {
        userCase.buildUseCase(null).single()
    }

    @Test(expected = IOException::class)
    fun `request with error from service`() = runTest {
        `given with error from service`()
        `then with error from service`()
    }

    private fun `given with error from service`() {
        coEvery {
            repository.requestWeatherForecast(any(), any(), any())
        }.throws(IOException("error"))
    }

    private suspend fun `then with error from service`() {
        userCase.buildUseCase(mockk(relaxed = true)).single()
    }
}
