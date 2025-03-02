package com.pavan.gamebay.feature.gameschedule.presentation.screens

import com.pavan.gamebay.core.presentaion.designsystem.ui.components.UIState
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.GameUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.game.TeamUIModel
import com.pavan.gamebay.feature.gameschedule.presentation.screens.components.gamesection.GameSectionUIModel

object PreviewConstants {
    val SAMPLE_HOME_TEAM = TeamUIModel(
        name = "Team A",
        triCode = "TA",
        record = "10-2",
        wins = 10,
        losses = 2,
        teamLogoUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png"
    )

    val SAMPLE_AWAY_TEAM = TeamUIModel(
        name = "Team B",
        triCode = "TB",
        record = "8-4",
        wins = 8,
        losses = 4,
        teamLogoUrl = "http://yc-app-resources.s3.amazonaws.com/nfl/logos/nfl_phi_light.png"
    )

    val SAMPLE_GAME_DATE = GameDate(
        dateText = "2022-10-10",
        dateTime = "10:00 AM"
    )

    val SAMPLE_GAME_UI_MODEL = GameUIModel(
        week = "Week 1",
        gameState = "Final",
        tv = "TV A",
        radio = "Radio A",
        gameOutcome = GameOutcome.WIN,
        awayScore = "100",
        homeScore = "110",
        gameType = GameType.SCHEDULED,
        gameDate = SAMPLE_GAME_DATE,
        opponentTeam = SAMPLE_AWAY_TEAM,
        homeTeam = SAMPLE_HOME_TEAM
    )

    val SAMPLE_GAME_SECTION_UI = GameSectionUIModel(
        heading = "Heading A",
        games = listOf(SAMPLE_GAME_UI_MODEL, SAMPLE_GAME_UI_MODEL, SAMPLE_GAME_UI_MODEL)
    )

    val SAMPLE_GAME_SCHEDULE_UI_STATE = GameScheduleUIState(
        defaultGameId = 1L,
        gameSections = UIState.Ready(listOf(SAMPLE_GAME_SECTION_UI, SAMPLE_GAME_SECTION_UI))
    )
}