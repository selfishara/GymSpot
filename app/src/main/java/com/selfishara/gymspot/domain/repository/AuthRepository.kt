package com.selfishara.gymspot.domain.repository

import com.selfishara.gymspot.core.result.ResultWrapper

/**
 * Repository contract for authentication-related operations.
 */
interface AuthRepository{

    /**
     * Registers a new user using email and password.
     *
     * @param email User email
     * @param password User password
     * @return Result of the sign up operation
     */
    suspend fun signUp(
        email: String,
        password: String
    ): ResultWrapper<Unit>
}