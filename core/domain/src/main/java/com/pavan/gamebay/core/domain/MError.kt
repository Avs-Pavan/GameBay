package com.pavan.gamebay.core.domain


/**
 * Represents a sealed interface for different types of errors.
 *
 * Use this to handle errors exhaustively with `when` expressions.
 */
sealed interface MError

/**
 * Represents an error related to network operations.
 *
 * @param message The error message describing the network issue.
 */
class NetworkError(val message: String) : MError

/**
 * Represents a generic error that does not fall under a specific category.
 *
 * @param message The error message.
 */
class GenericError(val message: String) : MError

/**
 * Represents an error related to data operations or processing.
 *
 * @param message The error message describing the data issue.
 */
class DataError(val message: String) : MError