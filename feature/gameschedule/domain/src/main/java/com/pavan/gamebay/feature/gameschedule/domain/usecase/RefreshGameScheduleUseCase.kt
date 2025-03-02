package com.pavan.gamebay.feature.gameschedule.domain.usecase

import com.pavan.gamebay.feature.gameschedule.domain.repo.IGameScheduleRepo
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Use case for refreshing the game schedule.
 *
 * @property gameScheduleRepository The repository to refresh the game schedule.
 */
@Singleton
class RefreshGameScheduleUseCase @Inject constructor(
    private val gameScheduleRepository: IGameScheduleRepo
) {
    /**
     * Invokes the use case to refresh the game schedule.
     */
    suspend operator fun invoke() {
        gameScheduleRepository.refreshGameSchedule()
    }
}