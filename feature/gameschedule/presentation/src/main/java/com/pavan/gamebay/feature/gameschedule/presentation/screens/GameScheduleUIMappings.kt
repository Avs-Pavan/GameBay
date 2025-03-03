package com.pavan.gamebay.feature.gameschedule.presentation.screens

import com.pavan.gamebay.feature.gameschedule.domain.models.Game
import com.pavan.gamebay.feature.gameschedule.domain.models.GameSection
import com.pavan.gamebay.feature.gameschedule.domain.models.Team
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.TeamUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUIModel


/**
 * Extension function to convert a GameSection domain model to a GameSectionUIModel.
 *
 * @param team The team associated with the game section.
 * @return A GameSectionUIModel representing the UI model of the game section.
 */
fun GameSection.toUIModel(team: Team): GameSectionUIModel {
    return GameSectionUIModel(
        heading = heading,
        games = games.sortedBy { it.label.toIntOrNull() ?: Int.MAX_VALUE }
            .map { it.toUIModel(team) }
    )
}

/**
 * Extension function to convert a Game domain model to a GameUIModel.
 *
 * @param homeTeam The home team associated with the game.
 * @return A GameUIModel representing the UI model of the game.
 */
fun Game.toUIModel(homeTeam: Team): GameUIModel {
    return GameUIModel(
        week = week,
        gameState = gameState,
        tv = tv,
        radio = radio,
        gameOutcome = gameOutcome,
        awayScore = awayScore.toString(),
        homeScore = homeScore,
        gameType = gameType,
        gameDate = gameDate,
        opponentTeam = opponentTeam.toUIModel(),
        homeTeam = homeTeam.toUIModel()
    )
}

/**
 * Extension function to convert a Team domain model to a TeamUIModel.
 *
 * @return A TeamUIModel representing the UI model of the team.
 */
fun Team.toUIModel(): TeamUIModel {
    return TeamUIModel(
        name = name,
        triCode = triCode,
        record = record,
        wins = wins,
        losses = losses,
        teamLogoUrl = "https://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_${triCode.lowercase()}_light.png"
    )
}