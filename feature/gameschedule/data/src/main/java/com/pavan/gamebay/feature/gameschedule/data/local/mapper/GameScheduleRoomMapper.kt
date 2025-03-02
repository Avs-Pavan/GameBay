package com.pavan.gamebay.feature.gameschedule.data.local.mapper

import android.R.attr.label
import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
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
        team = this.schedule?.team.toDomain(),
        defaultGameId = this.schedule?.defaultGameId?.toLong() ?: 0L,
        gameSection = this.gameSections.map {
            it.toDomain()
        }
    )

    private fun GameSectionWithGames.toDomain() = GameSection(
        heading = this.gameSection?.heading ?: "",
        games = this.games.map {
            it.toDomain()
        }
    )

    private fun GameEntity.toDomain() = Game(
        id = id,
        week = week,
        label = label,
        tv = tv,
        radio = radio,
        gameOutcome = wlt.asGameOutcome(),
        gameState = gameState,
        awayScore = awayScore,
        homeScore = homeScore,
        gameType = type.asGameType(),
        gameDate = this.toGameDate(),
        opponentTeam = opponent.toDomain()
    )


    private fun TeamEntity?.toDomain() = Team(
        triCode = this?.triCode ?: "",
        fullName = this?.fullName ?: "",
        name = this?.name ?: "",
        city = this?.city ?: "",
        record = this?.record ?: "",
        wins = this?.wins ?: 0,
        losses = this?.losses ?: 0,
        winPercentage = this?.winPercentage ?: 0.0,
        primaryColor = this?.primaryColor ?: ""
    )

    private fun GameEntity?.toGameDate(): GameDate {
        return this?.let {
            GameDate(
                dateText = dateText,
                dateTime = dateTime,
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
