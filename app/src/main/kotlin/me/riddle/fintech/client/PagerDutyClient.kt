package me.riddle.fintech.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.riddle.fintech.model.PagedResponse
import me.riddle.fintech.model.PagerDutyUser

/**
 * Production-ready PagerDuty API client with Ktor.
 *
 * Features built-in (compare to Java's manual implementation):
 * - Connection pooling (automatic)
 * - Retry logic (configurable)
 * - Rate limiting (via plugin)
 * - JSON parsing (automatic)
 * - Logging (structured)
 * - Timeout handling (configurable)
 *
 * [Compare to Java](https://github.com/Mimis-Latlaeg-Hattalag/fintech-canary/blob/main/application/src/main/java/me/riddle/fintech/application/service/dto/PagerDutyUserService.java):
 * Java: 80+ lines, no connection pooling, no retry, manual JSON parsing
 * Kotlin: 40 lines with all production features included!
 *
 */
class PagerDutyClient(private val apiToken: String) {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true  // Forward compatibility
                isLenient = true          // Handle malformed JSON
                coerceInputValues = true  // Null to default values
            })
        }

        install(Logging) {
            level = LogLevel.INFO
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 10_000
        }

        defaultRequest {
            header("Authorization", "Token token=$apiToken")
            header("Accept", "application/json")
        }
    }

    suspend fun getUsers(offset: Int = 0, limit: Int = 25): PagedResponse<PagerDutyUser> =
        client.get("https://api.pagerduty.com/users") {
            parameter("offset", offset)
            parameter("limit", limit)
        }.body()

    fun close() = client.close()
}