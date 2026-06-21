package com.vakans.chanpyona.di

import android.content.Context
import androidx.room.Room
import com.vakans.chanpyona.data.local.AppDatabase
import com.vakans.chanpyona.data.local.dao.*
import com.vakans.chanpyona.data.repository.*
import com.vakans.chanpyona.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "open_vakans_chanpyona_db"
        ).build()
    }

    @Provides
    fun provideTournamentSettingsDao(db: AppDatabase): TournamentSettingsDao {
        return db.tournamentSettingsDao()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideTeamDao(db: AppDatabase): TeamDao {
        return db.teamDao()
    }

    @Provides
    fun providePlayerDao(db: AppDatabase): PlayerDao {
        return db.playerDao()
    }

    @Provides
    fun provideMatchDao(db: AppDatabase): MatchDao {
        return db.matchDao()
    }

    @Provides
    fun provideStandingDao(db: AppDatabase): StandingDao {
        return db.standingDao()
    }

    @Provides
    fun provideNotificationDao(db: AppDatabase): NotificationDao {
        return db.notificationDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl

    @Singleton
    @Provides
    fun provideTeamRepository(impl: TeamRepositoryImpl): TeamRepository = impl

    @Singleton
    @Provides
    fun provideMatchRepository(impl: MatchRepositoryImpl): MatchRepository = impl

    @Singleton
    @Provides
    fun provideTournamentSettingsRepository(impl: TournamentSettingsRepositoryImpl): TournamentSettingsRepository = impl
}
