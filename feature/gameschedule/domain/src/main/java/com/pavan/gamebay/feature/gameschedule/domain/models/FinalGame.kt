package com.pavan.gamebay.feature.gameschedule.domain.models

/**
 * Data class representing a completed game ("F" type).
 *
 * @property week The week of the game.
 * @property label The label of the game.
 * @property tv The TV channel broadcasting the game.
 * @property radio The radio station broadcasting the game.
 * @property venue The venue where the game is played.
 * @property gameOutcome The outcome of the game.
 * @property gameState The state of the game.
 * @property clock The game clock.
 * @property quarterShorthand The shorthand notation for the quarter.
 * @property quarter The quarter of the game.
 * @property awayScore The score of the away team.
 * @property homeScore The score of the home team.
 * @property down The down information.
 * @property isHome Indicates if the game is a home game.
 * @property scheduleHeader The header for the game schedule.
 * @property isSuperStadium Indicates if the game is played in a super stadium.
 * @property cardData The card data associated with the game.
 * @property id The unique identifier of the game.
 * @property date The date of the game.
 * @property opponent The opponent team.
 * @property tickets The tickets information.
 * @property result The result of the game.
 * @property home Indicates if the game is a home game.
 * @property buttons The list of buttons associated with the game.
 * @property secondaryButtons The list of secondary buttons associated with the game.
 */
data class FinalGame(
    override val week: String,
    override val label: String,
    val tv: String,
    val radio: String,
    val venue: String,
    val gameOutcome: GameOutcome,
    override val gameState: String,
    override val clock: String,
    override val quarterShorthand: String,
    override val quarter: String,
    val awayScore: String,
    val homeScore: String,
    val down: String,
    val isHome: Boolean,
    override val scheduleHeader: String,
    val isSuperStadium: Boolean,
    override val cardData: CardData,
    override val id: Long,
    val date: GameDate,
    val opponent: Opponent,
    val tickets: Tickets,
    val result: String,
    val home: Boolean,
    val buttons: List<MButton>,
    val secondaryButtons: List<MButton>
) : Game