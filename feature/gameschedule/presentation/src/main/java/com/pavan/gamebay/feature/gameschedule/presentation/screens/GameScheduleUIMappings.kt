package com.pavan.gamebay.feature.gameschedule.presentation.screens

import com.pavan.gamebay.feature.gameschedule.domain.models.Game
import com.pavan.gamebay.feature.gameschedule.domain.models.GameSection
import com.pavan.gamebay.feature.gameschedule.domain.models.Team
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.TeamUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUIModel


fun GameSection.toUIModel(team: Team): GameSectionUIModel {
    return GameSectionUIModel(
        heading = heading,
        games = games.sortedBy { it.label.toInt() }.map { it.toUIModel(team) }
    )
}

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