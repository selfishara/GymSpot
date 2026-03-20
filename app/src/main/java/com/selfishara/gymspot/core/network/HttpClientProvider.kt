package com.selfishara.gymspot.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Provides a configured instance of Ktor HttpClient for making network requests.
 */
object HttpClientProvider {

    val client = HttpClient(OkHttp) {

        //Logging
        install(Logging) {
            level = LogLevel.ALL
        }

        //JSON Serialization
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }
}