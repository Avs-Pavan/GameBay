package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a ByeGame for bye weeks ("B" type).
 *
 * @property week The week of the game.
 * @property label The label for the game.
 * @property gameState The state of the game, default is "Bye".
 * @property clock The clock time, default is "-".
 * @property quarterShorthand The shorthand for the quarter.
 * @property quarter The quarter of the game.
 * @property scheduleHeader The header for the schedule.
 * @property cardData The card data associated with the game.
 * @property id The unique identifier for the game, default is 0.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property isHome Indicates if it is a home game, default is true.
 * @property home Indicates if it is a home game, default is true.
 * @property buttons The list of buttons associated with the game.
 * @property secondaryButtons The list of secondary buttons associated with the game.
 * @property isSuperStadium Indicates if the game is in a super stadium, default is false.
 */
data class ByeGame(
    override val week: String,
    override val label: String,
    override val gameState: String = "Bye",
    override val clock: String = "-",
    override val quarterShorthand: String = "",
    override val quarter: String = "",
    override val scheduleHeader: String,
    override val cardData: CardData,
    override val id: Long = 0,
    val tv: String = "",
    val radio: String = "",
    val isHome: Boolean = true,
    val home: Boolean = true,
    val buttons: List<MButton> = emptyList(),
    val secondaryButtons: List<MButton> = emptyList(),
    val isSuperStadium: Boolean = false
) : Game