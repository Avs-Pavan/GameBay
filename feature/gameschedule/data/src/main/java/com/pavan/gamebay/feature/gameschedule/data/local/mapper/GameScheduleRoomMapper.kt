package com.pavan.gamebay.feature.gameschedule.data.local.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameWithOpponent
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.domain.models.Game
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameSection
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.models.Team
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameScheduleRoomMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleWithDetails, Schedule> {
    override suspend fun map(input: ScheduleWithDetails): Schedule {
        return withContext(defaultDispatcher) {
            input.toDomain()
        }
    }

    private fun ScheduleWithDetails.toDomain() = Schedule(
        team = this.schedule.team.toDomain(),
        defaultGameId = this.schedule.defaultGameId.toLong(),
        gameSection = this.gameSections.map {
            it.toDomain()
        }
    )

    private fun GameSectionWithGames.toDomain() = GameSection(
        heading = this.gameSection.heading ?: "",
        game = this.games.map {
            it.toDomain()
        }
    )

    private fun GameWithOpponent.toDomain(): Game {
        return Game(
            id = this.game.id,
            week = this.game.week ?: "",
            gameState = this.game.gameState ?: "",
            tv = this.game.tv ?: "",
            radio = this.game.radio ?: "",
            awayScore = this.game.awayScore?.toInt() ?: 0,
            homeScore = this.game.homeScore?.toInt() ?: 0,
            gameOutcome = this.game.wlt.asGameOutcome(),
            gameType = this.game.type.asGameType(),
            gameDate = this.game.toGameDate(),
            opponentTeam = this.opponent.toDomain(),
        )
    }

    private fun TeamEntity?.toDomain(): Team {
        return this?.toDomain() ?: Team(
            triCode = "",
            fullName = "",
            name = "",
            city = "",
            record = "",
            wins = 0,
            losses = 0,
            winPercentage = 0.0,
            primaryColor = ""
        )
    }

    private fun GameEntity?.toGameDate(): GameDate {
        return this?.let {
            GameDate(
                dateText = dateText ?: "",
                dateTime = dateTime ?: "",
            )
        } ?: GameDate(
            dateText = "",
            dateTime = "",
        )
    }

    private fun String?.asGameOutcome() = when (this) {
        "W" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome.WIN
        "L" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome.LOSS
        "T" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome.TIE
        else -> com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome.NONE
    }

    private fun String?.asGameType() = when (this) {
        "F" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameType.FINAL
        "B" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameType.BYE
        "S" -> com.pavan.gamebay.feature.gameschedule.domain.models.GameType.SCHEDULED
        else -> com.pavan.gamebay.feature.gameschedule.domain.models.GameType.NONE
    }
}
