package com.selfishara.gymspot.domain.usecase

import com.selfishara.gymspot.domain.repository.AuthRepository
import com.selfishara.gymspot.core.result.ResultWrapper

/**
 * Use case for signing in an existing user.
 *
 * @param authRepository Repository for authentication operations
 */
class SignInUseCase(
    private val authRepository: AuthRepository
) {
    /**
     * Executes the sign in operation.
     *
     * @param email User's email address
     * @param password User's password
     * @return ResultWrapper containing success or error information
     */
    suspend operator fun invoke(email: String, password: String): ResultWrapper<Unit> {
        return authRepository.signIn(email, password)
    }
}