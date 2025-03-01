package com.pavan.gamebay.feature.gameschedule.domain.models


/**
 * Base Game interface with mandatory fields common to all game types.
 */
interface Game {
    /**
     * The week of the game.
     */
    val week: String

    /**
     * The label of the game.
     */
    val label: String

    /**
     * The current state of the game.
     */
    val gameState: String

    /**
     * The clock time of the game.
     */
    val clock: String

    /**
     * The shorthand notation for the current quarter of the game.
     */
    val quarterShorthand: String

    /**
     * The current quarter of the game.
     */
    val quarter: String

    /**
     * The header for the game schedule.
     */
    val scheduleHeader: String

    /**
     * The card data associated with the game.
     */
    val cardData: CardData

    /**
     * The unique identifier of the game.
     */
    val id: Long
}