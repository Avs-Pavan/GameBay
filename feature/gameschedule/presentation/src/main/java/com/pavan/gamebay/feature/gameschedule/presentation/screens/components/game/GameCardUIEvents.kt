package com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game

/**
 * Represents UI events for the Game Card.
 */
sealed interface GameCardUIEvents {
    /**
     * Event triggered when a game is clicked.
     *
     * @property gameUIModel The model representing the game that was clicked.
     */
    data class OnGameClick(val gameUIModel: GameUIModel) : GameCardUIEvents
}