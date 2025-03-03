package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Represents a section of games with a heading.
 *
 * @property heading The heading of the game section.
 * @property games The list of games in this section.
 */
data class GameSection(
    val heading: String,
    val games: List<Game>
)