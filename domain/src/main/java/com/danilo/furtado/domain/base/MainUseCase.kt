package com.danilo.furtado.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class MainUseCase<T, in Params> constructor(
    private val dispatcher: CoroutineDispatcher
) : BaseUseCase() {

    abstract suspend fun buildUseCase(params: Params? = null): Flow<T>

    open operator fun invoke(
        params: Params? = null,
        onResult: (T) -> Unit,
        onError: (e: Throwable) -> Unit,
        onStartProcess: (() -> Unit)? = null,
        onFinishProcess: (() -> Unit)? = null
    ) {
        disposeLast()
        coroutineContext = Job().plus(dispatcher).plus(Dispatchers.Main)
        CoroutineScope(coroutineContext).launch {
            buildUseCase(params)
                .onStart { onStartProcess?.invoke() }
                .onCompletion { onFinishProcess?.invoke() }
                .catch { exception ->
                    onError.invoke(exception)
                }
                .collect {
                    onResult.invoke(it)
                }
        }
    }
}
