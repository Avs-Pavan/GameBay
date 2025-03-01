package com.pavan.gamebay.core.presentaion.designsystem.ui.components

import androidx.compose.runtime.Composable

/**
 * Represents the state of a UI component.
 */
sealed class UIState<out T> {
    /**
     * Represents a loading state.
     */
    object Loading : UIState<Nothing>()

    /**
     * Represents a ready state with data.
     * @param data The data associated with the ready state.
     */
    data class Ready<T>(val data: T) : UIState<T>()

    /**
     * Represents an error state with a message.
     * @param message The error message.
     */
    data class Error(val message: String) : UIState<Nothing>()
}

/**
 * Executes the given block if the state is Loading.
 * @param block The block to execute.
 * @return The current state.
 */
fun <T> UIState<T>.onLoading(block: () -> Unit): UIState<T> {
    if (this is UIState.Loading) block()
    return this
}

/**
 * Executes the given block if the state is Ready.
 * @param block The block to execute with the data.
 * @return The current state.
 */
fun <T> UIState<T>.onReady(block: (T) -> Unit): UIState<T> {
    if (this is UIState.Ready) block(this.data)
    return this
}

/**
 * Executes the given block if the state is Error.
 * @param block The block to execute with the error message.
 * @return The current state.
 */
fun <T> UIState<T>.onError(block: (String) -> Unit): UIState<T> {
    if (this is UIState.Error) block(this.message)
    return this
}

/**
 * Handles the UI state by executing the appropriate composable function.
 * @param onLoading The composable function to execute if the state is Loading.
 * @param onReady The composable function to execute if the state is Ready.
 * @param onError The composable function to execute if the state is Error.
 */
@Composable
fun <T> UIState<T>.HandleState(
    onLoading: @Composable () -> Unit = {},
    onReady: @Composable (T) -> Unit = {},
    onError: @Composable (String) -> Unit = {}
) {
    when (this) {
        is UIState.Loading -> onLoading()
        is UIState.Ready -> onReady(data)
        is UIState.Error -> onError(message)
    }
}

/**
 * Executes the given composable function if the state is Ready.
 * @param onReady The composable function to execute with the data.
 */
@Composable
fun <T> UIState<T>.OnReady(
    onReady: @Composable (T) -> Unit
) {
    if (this is UIState.Ready) {
        onReady(data)
    }
}

/**
 * Executes the given composable function if the state is Error.
 * @param onError The composable function to execute with the error message.
 */
@Composable
fun <T> UIState<T>.OnError(
    onError: @Composable (String) -> Unit
) {
    if (this is UIState.Error) {
        onError(message)
    }
}

/**
 * Executes the given composable function if the state is Loading.
 * @param onLoading The composable function to execute.
 */
@Composable
fun <T> UIState<T>.OnLoading(
    onLoading: @Composable () -> Unit
) {
    if (this is UIState.Loading) {
        onLoading()
    }
}