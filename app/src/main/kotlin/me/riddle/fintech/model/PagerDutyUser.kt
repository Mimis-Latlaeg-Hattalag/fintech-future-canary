package me.riddle.fintech.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * PagerDuty User - clean, immutable, with automatic JSON serialization.
 * Unknown fields are handled at the client level with Json { ignoreUnknownKeys = true }
 *
 * [Compare to Java](https://github.com/Mimis-Latlaeg-Hattalag/fintech-canary/blob/main/domain/src/main/java/me/riddle/fintech/domain/model/dto/PagerDutyUser.java):
 * 70+ lines, manual builders, defensive copying, @JsonAnySetter complexity.
 * Kotlin: 27 lines, immutable by default, serialization built-in.
 *
 * FixMe: Implement usage.
 */
@Suppress("unused")
@Serializable
data class PagerDutyUser(
    val id: String,
    val name: String? = null,
    val email: String? = null,
    val summary: String? = null,
    val type: String = "user",
    val self: String? = null,

    @SerialName("html_url")
    val htmlUrl: String? = null,

    @SerialName("avatar_url")
    val avatarUrl: String? = null,

    val color: String? = null,
    val role: String? = null,
    val description: String? = null,

    @SerialName("invitation_sent")
    val invitationSent: Boolean? = null,

    @SerialName("job_title")
    val jobTitle: String? = null,

    @SerialName("time_zone")
    val timeZone: String? = null
)
