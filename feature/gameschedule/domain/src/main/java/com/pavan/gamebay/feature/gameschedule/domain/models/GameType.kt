package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Enum class representing different types of game statuses.
 */
enum class GameType {
    /**
     * Represents a final game.
     */
    FINAL,

    /**
     * Represents a bye game.
     */
    BYE,

    /**
     * Represents a scheduled game.
     */
    SCHEDULED,

    /**
     * Represents no game.
     */
    NONE
}