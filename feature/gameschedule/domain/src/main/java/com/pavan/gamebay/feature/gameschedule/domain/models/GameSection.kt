package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Represents a section of games with a heading.
 *
 * @property heading The heading of the game section.
 * @property game The list of games in this section.
 */
data class GameSection(
    val heading: String,
    val game: List<Game>
)