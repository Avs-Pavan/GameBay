package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection

import androidx.compose.runtime.Immutable
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameUIModel

/**
 * Immutable data class representing a section of games.
 *
 * @property heading The heading of the game section.
 * @property games The list of games in this section.
 */
@Immutable
data class GameSectionUIModel(
    val heading: String,
    val games: List<GameUIModel>,
)
