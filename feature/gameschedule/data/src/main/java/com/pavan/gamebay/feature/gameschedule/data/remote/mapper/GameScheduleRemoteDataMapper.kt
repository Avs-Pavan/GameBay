package com.pavan.gamebay.feature.gameschedule.data.remote.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameSectionResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.ScheduleResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.TeamResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameScheduleRemoteDataMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleResponse, ScheduleEntity> {
    override suspend fun map(input: ScheduleResponse): ScheduleEntity {
        return withContext(defaultDispatcher) {
            input.toEntity()
        }
    }

    private fun ScheduleResponse.toEntity(): ScheduleEntity {
        return ScheduleEntity(
            defaultGameId = defaultGameId ?: "",
            team = team?.toEntity(),
            gameSections = gameSections?.map { it.toEntity() } ?: emptyList()
        )
    }

    private fun GameSectionResponse.toEntity(): GameSectionEntity {
        return GameSectionEntity(
            heading = heading ?: "",
            games = games?.map { it.toEntity() } ?: emptyList()
        )
    }

    private fun GameResponse.toEntity(): GameEntity {
        return GameEntity(
            week = week ?: "",
            tv = tv ?: "",
            radio = radio ?: "",
            wlt = wlt ?: "",
            gameState = gameState ?: "",
            awayScore = awayScore ?: "",
            homeScore = homeScore ?: "",
            id = id ?: 0L,
            type = type ?: "",
            dateText = date?.text ?: "",
            dateTime = date?.time ?: "",
            dateTimestamp = date?.timestamp ?: "",
            opponent = opponent?.toEntity(),
            gameSectionId = 0L
        )
    }

    private fun TeamResponse.toEntity(): TeamEntity {
        return TeamEntity(
            triCode = triCode ?: "",
            fullName = fullName ?: "",
            name = name ?: "",
            city = city ?: "",
            record = record ?: "",
            wins = wins ?: 0,
            losses = losses ?: 0,
            winPercentage = winPercentage ?: 0.0,
            primaryColor = primaryColor ?: "",
        )
    }
}