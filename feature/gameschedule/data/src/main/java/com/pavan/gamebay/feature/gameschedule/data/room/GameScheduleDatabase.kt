package com.pavan.gamebay.feature.gameschedule.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pavan.gamebay.feature.gameschedule.data.local.dao.GameScheduleDao
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameSectionEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.jvm.java


/**
 * The Room database for the game schedule feature.
 */
@Database(
    entities = [
        ScheduleEntity::class,
        GameSectionEntity::class,
        GameEntity::class,
        TeamEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class GameScheduleDatabase : RoomDatabase() {

    /**
     * Provides access to the GameScheduleDao.
     */
    abstract fun scheduleDao(): GameScheduleDao

    companion object {
        const val DATABASE_NAME = "game_schedule_db"

        @Volatile
        private var INSTANCE: GameScheduleDatabase? = null

        /**
         * Returns the singleton instance of GameScheduleDatabase.
         *
         * @param context The application context.
         * @return The singleton instance of GameScheduleDatabase.
         */
        fun getDatabase(@ApplicationContext context: Context): GameScheduleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameScheduleDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}