package com.pavan.gamebay.feature.gameschedule.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleWithDetails
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameScheduleDao {

    /**
     * Retrieves the schedule with its details.
     *
     * @return A Flow emitting the ScheduleWithDetails object or null.
     */
    @Transaction
    @Query("SELECT * FROM ScheduleEntity")
    fun getScheduleWithDetails(): Flow<ScheduleWithDetails?>

    /**
     * Inserts a schedule into the database.
     *
     * @param schedule The ScheduleEntity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)

    /**
     * Inserts a game section into the database.
     *
     * @param gameSection The GameSectionEntity to be inserted.
     * @return The row ID of the inserted game section.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameSection(gameSection: GameSectionEntity): Long

    /**
     * Inserts a list of games into the database.
     *
     * @param games The list of GameEntity objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    /**
     * Inserts a team into the database.
     *
     * @param team The TeamEntity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity)

}