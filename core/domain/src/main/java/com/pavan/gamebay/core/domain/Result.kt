package com.pavan.gamebay.core.domain

/**
 * Represents a result of an operation that can either be a [Success] with a value of type [D]
 * or an [Error] with an error of type [E].
 */
sealed interface Result<out D, out E : MError> {
    /**
     * Represents a successful operation with a data value of type [D].
     *
     * @param data The data value associated with the success.
     */
    data class Success<out D, out E : MError>(val data: D) : Result<D, E>

    /**
     * Represents a failed operation with an error of type [E].
     *
     * @param error The error associated with the failure.
     */
    data class Error<out D, out E : MError>(val error: E) : Result<D, E>
}

/**
 * Executes the given [action] if this [Result] is a [Result.Success].
 *
 * @param action The action to execute with the data if the result is a [Result.Success].
 * @return This [Result] instance.
 */
suspend inline fun <T> Result<T, MError>.onSuccess(
    crossinline action: suspend (T) -> Unit
): Result<T, MError> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

/**
 * Executes the given [action] if this [Result] is an [Error].
 *
 * @param action The action to execute with the error if the result is an [Error].
 * @return This [Result] instance.
 */
suspend inline fun <T> Result<T, MError>.onError(
    crossinline action: suspend (MError) -> Unit
): Result<T, MError> {
    if (this is Result.Error) {
        action(error)
    }
    return this
}