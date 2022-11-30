package com.danilo.furtado.theweatherapp.base

import androidx.lifecycle.ViewModel
import com.danilo.furtado.domain.base.BaseUseCase

abstract class BaseViewModel(
    vararg useCases: BaseUseCase
) : ViewModel() {
    private val useCaseToDispose = useCases.toList()
    override fun onCleared() {
        useCaseToDispose.forEach { it.dispose() }
        super.onCleared()
    }
}
