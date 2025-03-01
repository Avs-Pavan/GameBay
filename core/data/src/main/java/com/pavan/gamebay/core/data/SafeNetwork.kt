package com.pavan.gamebay.core.data

import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.core.domain.MError
import com.pavan.gamebay.core.domain.NetworkError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.core.domain.map
import retrofit2.Response


/**
 * Executes a network call safely and maps the response using the provided mapper.
 *
 * This function wraps a network request, automatically handling exceptions and
 * converting the `Response` object to a `Result` object. It then maps the successful
 * response body to another type using the provided mapper.
 *
 * @param T The type of the expected response body.
 * @param R The type to which the response body will be mapped.
 * @param mapper An instance of [IMapper] to map the response body.
 * @param execute A lambda representing the network call to execute.
 * @return A [Result] containing either the mapped response body or an [MError].
 */
suspend inline fun <reified T, R> mappedSafeCall(
    mapper: IMapper<T, R>,
    crossinline execute: suspend () -> Response<T>
): Result<R, MError> {
    return safeCall {
        execute.invoke()
    }.map {
        mapper.map(it)
    }
}


/**
 * Executes a network call safely, handling exceptions and processing the response.
 *
 * This function wraps a network request, automatically handling exceptions and
 * converting the `Response` object to a `Result` object. It also handles cases
 * where the response body is null.
 *
 * @param T The type of the expected response body.
 * @param execute A lambda representing the network call to execute.
 * @return A [Result] containing either the response body or a [NetworkError].
 */
suspend inline fun <reified T> safeCall(
    crossinline execute: suspend () -> Response<T>
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: Exception) {
        return Result.Error(
            NetworkError(
                e.message ?: "Unknown error"
            )
        )
    }
    return responseToResult<T>(response)
}

/**
 * Converts a Retrofit [Response] to a [Result].
 *
 * This function checks the HTTP status code of the response. If the status code
 * is in the 200-299 range, it returns a [Result.Success] containing the response body.
 * If the response body is null, it returns a [Result.Error] indicating that the
 * response body is null. Otherwise, it returns a [Result.Error] with the HTTP
 * status code and error body.
 *
 * @param T The type of the expected response body.
 * @param response The Retrofit [Response] to convert.
 * @return A [Result] containing either the response body or a [NetworkError].
 */
inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> {
    if (response.code() in 200..299) {
        return response.body()?.let {
            return Result.Success(it)
        } ?: run {
            Result.Error(NetworkError("Response body is null"))
        }
    } else {
        return Result.Error(NetworkError("Error: ${response.code()}, ${response.errorBody()}"))
    }
}