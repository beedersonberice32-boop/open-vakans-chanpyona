package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.TournamentSettings
import kotlinx.coroutines.flow.Flow

interface TournamentSettingsRepository {
    fun getTournamentSettings(): Flow<TournamentSettings?>
    suspend fun updateTournamentSettings(settings: TournamentSettings): Result<Unit>
    suspend fun initializeDefaultSettings(): Result<Unit>
}
