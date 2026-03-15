package com.selfishara.gymspot.core.result

/**
 * Represents the result of a data operation within the application.
 *
 * This wrapper standardizes how success, loading, and error states
 * are handled across repositories, use cases, and ViewModels.
 *
 * @param T The type of data returned when the operation succeeds.
 */
sealed class ResultWrapper<out T> {

    /**
     * Represents a successful result containing data.
     *
     * @param data The resulting data of the operation.
     */
    data class Success<T>(
        val data: T
    ) : ResultWrapper<T>()

    /**
     * Represents a failed result.
     *
     * @param message A human-readable error message.
     * @param throwable The original exception, if available.
     */
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : ResultWrapper<Nothing>()

    /**
     * Represents an operation that is currently in progress.
     */
    data object Loading : ResultWrapper<Nothing>()
}