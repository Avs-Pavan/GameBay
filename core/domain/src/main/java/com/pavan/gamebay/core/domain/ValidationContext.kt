package com.pavan.gamebay.core.domain

/**
 * Abstract class representing a validation context.
 *
 * @param T The type of data being validated.
 * @property data The data to be validated.
 */
abstract class ValidationContext<T>(val data: T) {
    /**
     * Checks a condition on the data.
     *
     * @param condition The condition to check.
     * @param message The error message if the condition fails.
     */
    abstract suspend fun check(condition: suspend (T) -> Boolean, message: String)

    /**
     * Gets the result of the validation.
     *
     * @return The result of the validation.
     */
    abstract fun getResult(): Result<T, MError>
}

/**
 * Validation context that fails fast on the first error.
 *
 * @param T The type of data being validated.
 * @param data The data to be validated.
 */
class FailFastValidationContext<T>(data: T) : ValidationContext<T>(data) {
    private var failedError: ValidationFailedError? = null

    /**
     * Checks a condition on the data and fails fast if the condition fails.
     *
     * @param condition The condition to check.
     * @param message The error message if the condition fails.
     */
    override suspend fun check(condition: suspend (T) -> Boolean, message: String) {
        if (failedError == null && !condition(data)) {
            failedError = ValidationFailedError(message)
        }
    }

    /**
     * Gets the result of the validation.
     *
     * @return The result of the validation.
     */
    override fun getResult(): Result<T, MError> {
        return failedError?.let { Result.Error(it) } ?: Result.Success(data)
    }
}

/**
 * Validation context that collects all errors.
 *
 * @param T The type of data being validated.
 * @param data The data to be validated.
 */
class RegularValidationContext<T>(data: T) : ValidationContext<T>(data) {
    private val errors = mutableListOf<ValidationFailedError>()

    /**
     * Checks a condition on the data and collects errors if the condition fails.
     *
     * @param condition The condition to check.
     * @param message The error message if the condition fails.
     */
    override suspend fun check(condition: suspend (T) -> Boolean, message: String) {
        if (!condition(data)) {
            errors.add(ValidationFailedError(message))
        }
    }

    /**
     * Gets the result of the validation.
     *
     * @return The result of the validation.
     */
    override fun getResult(): Result<T, MError> {
        return when {
            errors.isEmpty() -> Result.Success(data)
            errors.size == 1 -> Result.Error(errors.first())
            else -> Result.Error(MultipleValidationErrors(errors))
        }
    }
}