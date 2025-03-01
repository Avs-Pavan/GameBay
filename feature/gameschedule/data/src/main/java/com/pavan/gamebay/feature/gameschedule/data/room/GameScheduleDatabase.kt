package com.pavan.gamebay.feature.gameschedule.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pavan.gamebay.feature.gameschedule.data.local.dao.GameScheduleDao
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ButtonEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.CardDataEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.FilterItemEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameDateEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.GameEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.OpponentEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.ScheduleEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TeamEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.TicketsEntity
import com.pavan.gamebay.feature.gameschedule.data.local.entities.YinzNodeEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.jvm.java


@Database(
    entities = [
        GameEntity::class,
        ScheduleEntity::class,
        TeamEntity::class,
        CardDataEntity::class,
        GameDateEntity::class,
        OpponentEntity::class,
        TicketsEntity::class,
        ButtonEntity::class,
        FilterEntity::class,
        FilterItemEntity::class,
        YinzNodeEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class GameScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): GameScheduleDao

    companion object {
        const val DATABASE_NAME = "game_schedule_db"

        @Volatile
        private var INSTANCE: GameScheduleDatabase? = null

        fun getDatabase(@ApplicationContext context: Context): GameScheduleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameScheduleDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}