package com.selfishara.gymspot.data.repository

import com.selfishara.gymspot.core.network.SupabaseClientProvider
import com.selfishara.gymspot.core.result.ResultWrapper
import com.selfishara.gymspot.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth

/**
 * Implementation of [AuthRepository] using Supabase Auth.
 */
class AuthRepositoryImpl : AuthRepository {

    override suspend fun signUp(email: String, password: String): ResultWrapper<Unit> {
        return try {
            SupabaseClientProvider.client.auth.signUpWith(io.github.jan.supabase.auth.providers.builtin.Email) {
                this.email = email
                this.password = password
            }

            ResultWrapper.Success(Unit)
        } catch (exception: Exception) {
            ResultWrapper.Error(
                message = exception.message ?: "An unexpected error occurred during sign up.",
                throwable = exception
            )
        }
    }
}