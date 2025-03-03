package com.pavan.gamebay.feature.gameschedule.data.local.mapper

import com.pavan.gamebay.core.domain.DefaultDispatcher
import com.pavan.gamebay.core.domain.IMapper
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionWithGames
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.domain.models.Game
import com.pavan.gamebay.feature.gameschedule.domain.models.GameDate
import com.pavan.gamebay.feature.gameschedule.domain.models.GameOutcome
import com.pavan.gamebay.feature.gameschedule.domain.models.GameSection
import com.pavan.gamebay.feature.gameschedule.domain.models.GameType
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.models.Team
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

/**
 * Mapper class to convert between `ScheduleWithDetails` and `Schedule` domain models.
 *
 * @property defaultDispatcher The default coroutine dispatcher for executing mapping operations.
 */
class GameScheduleRoomMapper @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : IMapper<ScheduleWithDetails, Schedule> {

    /**
     * Maps a `ScheduleWithDetails` object to a `Schedule` object.
     *
     * @param input The `ScheduleWithDetails` object to be mapped.
     * @return The mapped `Schedule` object.
     */
    override suspend fun map(input: ScheduleWithDetails): Schedule {
        return withContext(defaultDispatcher) {
            input.toDomain()
        }
    }

    /**
     * Extension function to map `ScheduleWithDetails` to `Schedule`.
     *
     * @return The mapped `Schedule` object.
     */
    private fun ScheduleWithDetails.toDomain() = Schedule(
        team = this.schedule?.team.toDomain(),
        defaultGameId = this.schedule?.defaultGameId?.toLong() ?: 0L,
        gameSection = this.gameSections.map {
            it.toDomain()
        }
    )

    /**
     * Extension function to map `GameSectionWithGames` to `GameSection`.
     *
     * @return The mapped `GameSection` object.
     */
    private fun GameSectionWithGames.toDomain() = GameSection(
        heading = this.gameSection?.heading ?: "",
        games = this.games.map {
            it.toDomain()
        }
    )

    /**
     * Extension function to map `GameEntity` to `Game`.
     *
     * @return The mapped `Game` object.
     */
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

    /**
     * Extension function to map `TeamEntity` to `Team`.
     *
     * @return The mapped `Team` object.
     */
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

    /**
     * Extension function to map `GameEntity` to `GameDate`.
     *
     * @return The mapped `GameDate` object.
     */
    private fun GameEntity?.toGameDate(): GameDate {
        return this?.let {
            GameDate(
                dateText = dateTimestamp.toLocalFormattedDate(),
                dateTime = dateTimestamp.toLocalFormattedTime(),
            )
        } ?: GameDate(
            dateText = "",
            dateTime = "",
        )
    }

    // Extension for date part (DDD, MMM, dd)
    fun String.toLocalFormattedDate(): String {
        try {


            val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            utcFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = utcFormat.parse(this) ?: return "Invalid date"

            val dateFormat = SimpleDateFormat("EEE, MMM dd", Locale.US)
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(date)
        } catch (e: Exception) {
            return ""
        }
    }

    // Extension for time part (hh:mm a)
    fun String.toLocalFormattedTime(): String {
        try {
            val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            utcFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = utcFormat.parse(this) ?: return "Invalid time"

            val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
            timeFormat.timeZone = TimeZone.getDefault()
            return timeFormat.format(date)
        } catch (e: Exception) {
            return ""
        }
    }

    /**
     * Extension function to map a nullable `String` to `GameOutcome`.
     *
     * @return The mapped `GameOutcome` object.
     */
    private fun String?.asGameOutcome() = when (this) {
        "W" -> GameOutcome.WIN
        "L" -> GameOutcome.LOSS
        "T" -> GameOutcome.TIE
        else -> GameOutcome.NONE
    }

    /**
     * Extension function to map a nullable `String` to `GameType`.
     *
     * @return The mapped `GameType` object.
     */
    private fun String?.asGameType() = when (this) {
        "F" -> GameType.FINAL
        "B" -> GameType.BYE
        "S" -> GameType.SCHEDULED
        else -> GameType.NONE
    }
}