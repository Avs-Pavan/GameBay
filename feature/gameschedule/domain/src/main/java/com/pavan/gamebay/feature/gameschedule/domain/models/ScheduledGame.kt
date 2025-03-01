package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a scheduled game for upcoming or postseason games ("S" type).
 *
 * @property week The week of the game.
 * @property label The label of the game.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property venue The venue where the game is held.
 * @property gameState The current state of the game.
 * @property clock The game clock.
 * @property quarterShorthand The shorthand representation of the quarter.
 * @property quarter The current quarter of the game.
 * @property isHome Indicates if the game is a home game.
 * @property scheduleHeader The header for the game schedule.
 * @property isSuperStadium Indicates if the game is in a super stadium.
 * @property cardData The card data associated with the game.
 * @property id The unique identifier of the game.
 * @property date The date of the game.
 * @property opponent The opponent team.
 * @property tickets The tickets information for the game.
 * @property home Indicates if the game is a home game (duplicate of isHome).
 * @property buttons The list of buttons associated with the game.
 * @property secondaryButtons The list of secondary buttons associated with the game.
 */
data class ScheduledGame(
    override val week: String,
    override val label: String,
    val tv: String,
    val radio: String,
    val venue: String,
    override val gameState: String,
    override val clock: String,
    override val quarterShorthand: String,
    override val quarter: String,
    val isHome: Boolean,
    override val scheduleHeader: String,
    val isSuperStadium: Boolean,
    override val cardData: CardData,
    override val id: Long,
    val date: GameDate,
    val opponent: Opponent,
    val tickets: Tickets,
    val home: Boolean,
    val buttons: List<MButton>,
    val secondaryButtons: List<MButton>
) : Game