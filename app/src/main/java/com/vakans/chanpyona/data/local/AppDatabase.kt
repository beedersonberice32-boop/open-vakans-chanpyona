package com.vakans.chanpyona.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vakans.chanpyona.data.local.converter.LocalDateTimeConverter
import com.vakans.chanpyona.data.local.dao.*
import com.vakans.chanpyona.data.local.entity.*

@Database(
    entities = [
        TournamentSettingsEntity::class,
        UserEntity::class,
        TeamEntity::class,
        PlayerEntity::class,
        DocumentEntity::class,
        GroupEntity::class,
        MatchEntity::class,
        StandingEntity::class,
        ScorerEntity::class,
        YellowCardEntity::class,
        RedCardEntity::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tournamentSettingsDao(): TournamentSettingsDao
    abstract fun userDao(): UserDao
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
    abstract fun matchDao(): MatchDao
    abstract fun standingDao(): StandingDao
    abstract fun notificationDao(): NotificationDao
}
