package com.selfishara.gymspot.domain.usecase

import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.domain.repository.AuthRepository

/**
 * Use case responsible for registering a new user.
 */
class SignUpUseCase(
    private val authRepository: AuthRepository
) {

    /**
     * Executes the sign up operation.
     *
     * @param email User email
     * @param password User password
     */
    suspend operator fun invoke(email: String, password: String): ResultWrapper<Unit> {
        return authRepository.signUp(email, password)
    }
}