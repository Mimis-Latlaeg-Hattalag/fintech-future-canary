package me.riddle.fintech.model

import kotlinx.serialization.Serializable

/**
 * Generic paginated response wrapper for PagerDuty API.
 *
 * [Compare to Java](https://github.com/Mimis-Latlaeg-Hattalag/fintech-canary/blob/main/domain/src/main/java/me/riddle/fintech/domain/model/dto/PagedResponse.java):
 * 100+ lines with defensive copying, validation, calculated properties.
 * Kotlin: 20 lines, immutable, with extension functions for the rest.
 *
 */
@Serializable
data class PagedResponse<T>(
    val limit: Int,
    val offset: Int,
    val more: Boolean,
    val total: Int? = null,
    val users: List<T> = emptyList()  // PagerDuty wraps in "users" field
) {
    // Simple computed properties
    val hasMore: Boolean get() = more
    @Suppress("unused")
    val isEmpty: Boolean get() = users.isEmpty()                        // Is expected in actual use.
    val nextOffset: Int get() = offset + limit
}

// Extension functions for clean pagination
@Suppress("unused")
fun <T> PagedResponse<T>.isFirstPage(): Boolean = offset == 0           // Is expected in actual use
@Suppress("unused")
fun <T> PagedResponse<T>.currentPage(): Int = (offset / limit) + 1      // Is expected in actual use