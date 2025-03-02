package com.pavan.gamebay.feature.gameschedule.domain.usecase

import com.pavan.gamebay.core.domain.MError
import com.pavan.gamebay.core.domain.Result
import com.pavan.gamebay.core.domain.validate
import com.pavan.gamebay.feature.gameschedule.domain.models.Schedule
import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case for retrieving the game schedule.
 *
 * @property gameScheduleRepo Repository to access game schedule data.
 */

@Singleton
class GetGameScheduleUseCase @Inject constructor(
    private val gameScheduleRepo: IGameScheduleRepo,
) {
    /**
     * Invokes the use case to get the game schedule.
     *
     * @return A Flow emitting the result of the game schedule retrieval.
     * The result can be either a successful `Schedule` or an `MError`.
     */
    operator fun invoke(): Flow<Result<Schedule, MError>> {
        return gameScheduleRepo.getGameSchedule().validate {
            // Validate the schedule data before emitting it - ensure team name is not empty
            check(data.team.fullName.isNotEmpty()) { "Team name cannot be empty" }
        }
    }
}