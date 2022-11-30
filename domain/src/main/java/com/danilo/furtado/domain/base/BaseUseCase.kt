package com.danilo.furtado.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.ensureActive

abstract class BaseUseCase : CoroutineScope {

    final override var coroutineContext: CoroutineContext = Job()

    protected fun disposeLast() {
        dispose()
    }

    fun dispose() {
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }
}
