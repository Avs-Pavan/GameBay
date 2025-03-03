package com.pavan.gamebay.feature.gameschedule.data.remote.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.GameSectionResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.ScheduleResponse
import com.pavan.gamebay.feature.gameschedule.data.remote.model.TeamResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Mapper class to convert remote data models to local data models.
 *
 * @property defaultDispatcher The default coroutine dispatcher.
 */
class GameScheduleRemoteDataMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleResponse, ScheduleWithDetails> {

    /**
     * Maps a [ScheduleResponse] to [ScheduleWithDetails].
     *
     * @param input The [ScheduleResponse] to map.
     * @return The mapped [ScheduleWithDetails].
     */
    override suspend fun map(input: ScheduleResponse): ScheduleWithDetails {
        return withContext(defaultDispatcher) {
            input.scheduleWithDetails()
        }
    }

    /**
     * Extension function to map [ScheduleResponse] to [ScheduleWithDetails].
     *
     * @receiver The [ScheduleResponse] to map.
     * @return The mapped [ScheduleWithDetails].
     */
    private fun ScheduleResponse.scheduleWithDetails(): ScheduleWithDetails {
        return ScheduleWithDetails(
            schedule = this.toEntity(),
            gameSections = gameSections?.map { it.toGameSectionWithGames() } ?: emptyList()
        )
    }

    /**
     * Extension function to map [GameSectionResponse] to [GameSectionWithGames].
     *
     * @receiver The [GameSectionResponse] to map.
     * @return The mapped [GameSectionWithGames].
     */
    private fun GameSectionResponse.toGameSectionWithGames(): GameSectionWithGames {
        return GameSectionWithGames(
            gameSection = this.toEntity(),
            games = games?.map { it.toEntity() } ?: emptyList()
        )
    }

    /**
     * Extension function to map [ScheduleResponse] to [ScheduleEntity].
     *
     * @receiver The [ScheduleResponse] to map.
     * @return The mapped [ScheduleEntity].
     */
    private fun ScheduleResponse.toEntity(): ScheduleEntity {
        return ScheduleEntity(
            defaultGameId = defaultGameId ?: "",
            team = team?.toEntity() ?: TeamEntity(),
        )
    }

    /**
     * Extension function to map [GameSectionResponse] to [GameSectionEntity].
     *
     * @receiver The [GameSectionResponse] to map.
     * @return The mapped [GameSectionEntity].
     */
    private fun GameSectionResponse.toEntity(): GameSectionEntity {
        return GameSectionEntity(
            heading = heading ?: "",
            scheduleId = 0L,
        )
    }

    /**
     * Extension function to map [GameResponse] to [GameEntity].
     *
     * @receiver The [GameResponse] to map.
     * @return The mapped [GameEntity].
     */
    private fun GameResponse.toEntity(): GameEntity {
        return GameEntity(
            week = week ?: "",
            label = label ?: "",
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
            opponent = opponent?.toEntity() ?: TeamEntity(),
            gameSectionHeading = ""
        )
    }

    /**
     * Extension function to map [TeamResponse] to [TeamEntity].
     *
     * @receiver The [TeamResponse] to map.
     * @return The mapped [TeamEntity].
     */
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