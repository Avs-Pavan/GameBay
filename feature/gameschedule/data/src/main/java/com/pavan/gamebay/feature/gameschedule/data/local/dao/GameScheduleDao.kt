package com.pavan.gamebay.feature.gameschedule.data.local.dao

import androidx.room.*
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ButtonEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterItemEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithRelations
import kotlinx.coroutines.flow.Flow

@Dao
interface GameScheduleDao {

    // Insert operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameSection(section: GameSectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertButtons(buttons: List<ButtonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilter(filter: FilterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilterItems(items: List<FilterItemEntity>)

    // Query to get complete schedule with relationships
    @Transaction
    @Query("SELECT * FROM schedules WHERE scheduleId = :scheduleId")
    fun getScheduleFlow(scheduleId: Long): Flow<ScheduleWithRelations?>

    // Clear existing data (optional, if you want to refresh the entire schedule)
    @Query("DELETE FROM schedules WHERE scheduleId = :scheduleId")
    suspend fun clearSchedule(scheduleId: Long)

    @Query("DELETE FROM game_sections WHERE scheduleId = :scheduleId")
    suspend fun clearGameSections(scheduleId: Long)

    @Query("DELETE FROM games WHERE sectionHeading IN (SELECT heading FROM game_sections WHERE scheduleId = :scheduleId)")
    suspend fun clearGames(scheduleId: Long)

    @Query("DELETE FROM buttons WHERE gameId IN (SELECT id FROM games WHERE sectionHeading IN (SELECT heading FROM game_sections WHERE scheduleId = :scheduleId))")
    suspend fun clearButtons(scheduleId: Long)

    @Query("DELETE FROM filters WHERE scheduleId = :scheduleId")
    suspend fun clearFilters(scheduleId: Long)

    @Query("DELETE FROM filter_items WHERE filterQueryParameter IN (SELECT queryParameter FROM filters WHERE scheduleId = :scheduleId)")
    suspend fun clearFilterItems(scheduleId: Long)
}
