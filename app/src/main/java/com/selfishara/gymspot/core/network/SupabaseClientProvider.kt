package com.selfishara.gymspot.core.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

/**
 * Provides a shared Supabase client instance for the application.
 *
 * The client is configured with the modules required for the initial
 * project scope: authentication and PostgREST database access.
 */
object SupabaseClientProvider {

    val client = createSupabaseClient(
        supabaseUrl = SupabaseConfig.SUPABASE_URL,
        supabaseKey = SupabaseConfig.SUPABASE_KEY
    ) {
        install(Postgrest)
        install(Auth)
    }
}