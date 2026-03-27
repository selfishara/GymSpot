package com.selfishara.gymspot.domain.usecase

import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.domain.repository.AuthRepository

/**
 * Use case responsible for signing out the current user.
 */
class SignOutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): ResultWrapper<Unit> {
        return authRepository.signOut()
    }
}