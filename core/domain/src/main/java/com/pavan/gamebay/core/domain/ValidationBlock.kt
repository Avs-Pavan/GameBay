package com.pavan.gamebay.core.domain


/**
 * A data class representing a validation block.
 *
 * @param T The type of the context.
 * @param failFast A boolean indicating whether the validation should fail fast.
 * @param block A suspend function that defines the validation logic within a [ValidationContext].
 */
data class ValidationBlock<T>(
    val failFast: Boolean,
    val block: suspend ValidationContext<T>.() -> Unit
)