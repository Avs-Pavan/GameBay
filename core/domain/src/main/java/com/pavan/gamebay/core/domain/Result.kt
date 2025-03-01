package com.pavan.gamebay.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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


/**
 * Validates the data of a [Result.Success] using the given validation block.
 * Supports fail-fast or regular validation modes.
 *
 * @param failFast If true, stops at the first validation failure; if false, runs all validations and collects errors.
 * @param block The DSL block defining validation rules.
 * @return The original [Result] if all validations pass, or a [Result.Error] with [ValidationFailedError] or [MultipleValidationErrors].
 */
suspend inline fun <T> Result<T, MError>.validate(
    failFast: Boolean = false,
    crossinline block: suspend ValidationContext<T>.() -> Unit
): Result<T, MError> {
    return when (this) {
        is Result.Success -> {
            val context =
                if (failFast) FailFastValidationContext(data) else RegularValidationContext(data)
            context.block()
            context.getResult()
        }

        is Result.Error -> this
    }
}


/**
 * Validates the data of a [Result.Success] using the given validation blocks.
 * Supports fail-fast or regular validation modes.
 *
 * @param blocks The validation blocks to apply to the data.
 * @return The original [Result] if all validations pass, or a [Result.Error] with [ValidationFailedError] or [MultipleValidationErrors].
 */
suspend inline fun <T> Result<T, MError>.validate(
    vararg blocks: ValidationBlock<T>
): Result<T, MError> {
    return when (this) {
        is Result.Success -> {
            val allErrors = mutableListOf<ValidationFailedError>()
            for (block in blocks) {
                val context =
                    if (block.failFast) FailFastValidationContext(data) else RegularValidationContext(
                        data
                    )
                block.block(context)
                val result = context.getResult()
                if (result is Result.Error) {
                    when (val error = result.error) {
                        is ValidationFailedError -> allErrors.add(error)
                        is MultipleValidationErrors -> allErrors.addAll(error.errors)
                        else -> return result // Non-validation error, propagate immediately
                    }
                    if (block.failFast) break // Stop after fail-fast block
                }
            }
            if (allErrors.isEmpty()) this
            else Result.Error(
                if (allErrors.size == 1) allErrors.first()
                else MultipleValidationErrors(allErrors)
            )
        }

        is Result.Error -> this
    }
}


/**
 * Intercepts a [Flow] of [Result] and validates each [Result.Success] emission using the given validation block.
 *
 * @param failFast If true, stops at the first validation failure; if false, runs all validations and collects errors.
 * @param block The DSL block defining validation rules for the [Result.Success] data.
 * @return A new [Flow] with validated results, transforming failed validations into [Result.Error].
 */
inline fun <T> Flow<Result<T, MError>>.validate(
    failFast: Boolean = false,
    crossinline block: suspend ValidationContext<T>.() -> Unit
): Flow<Result<T, MError>> {
    return this.map { result ->
        when (result) {
            is Result.Success -> {
                val context =
                    if (failFast) FailFastValidationContext(result.data) else RegularValidationContext(
                        result.data
                    )
                context.block()
                context.getResult()
            }

            is Result.Error -> result // Propagate errors unchanged
        }
    }
}