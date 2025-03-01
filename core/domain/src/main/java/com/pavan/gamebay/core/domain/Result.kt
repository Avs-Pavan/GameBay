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


/**
 * Transforms the data of a [Result.Success] using the given [transform] function.
 * If the result is a [Result.Error], the error is propagated unchanged.
 *
 * @param transform The function to transform the data of a [Result.Success].
 * @return A new [Result] with the transformed data if the original result was a [Result.Success],
 *         or the original error if the result was a [Result.Error].
 */
suspend inline fun <T, R> Result<T, MError>.map(crossinline transform: suspend (T) -> R): Result<R, MError> {
    return when (this) {
        is Result.Success -> Result.Success(transform(data))
        is Result.Error -> Result.Error(error)
    }
}