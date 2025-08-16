package me.riddle.fintech

import kotlinx.coroutines.runBlocking
import me.riddle.fintech.client.PagerDutyClient

/**
 * Demonstrates production-quality PagerDuty API pagination in Kotlin.
 *
 * To run:
 * export PAGERDUTY_API_TOKEN=your_token_here
 * ./gradlew run
 */
fun main() = runBlocking {
    val token = System.getenv("PAGERDUTY_API_TOKEN")
        ?: error("Set PAGERDUTY_API_TOKEN environment variable")

    PagerDutyClient(token).use { client ->
        println("Fetching PagerDuty users...")

        val response = client.getUsers(offset = 0, limit = 10)

        println("Found ${response.users.size} users (total: ${response.total})")
        response.users.forEach { user ->
            println("  • ${user.name} (${user.email})")
        }

        if (response.hasMore) {
            println("\nMore pages available. Next offset: ${response.nextOffset}")
        }
    }
}

// Extension function for auto-closing
inline fun <T> PagerDutyClient.use(block: (PagerDutyClient) -> T): T =
    try { block(this) } finally { close() }