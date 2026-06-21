package com.vakans.chanpyona.data.repository

import com.vakans.chanpyona.data.local.dao.TournamentSettingsDao
import com.vakans.chanpyona.data.local.entity.TournamentSettingsEntity
import com.vakans.chanpyona.domain.model.TournamentSettings
import com.vakans.chanpyona.domain.repository.TournamentSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TournamentSettingsRepositoryImpl @Inject constructor(
    private val settingsDao: TournamentSettingsDao
) : TournamentSettingsRepository {

    override fun getTournamentSettings(): Flow<TournamentSettings?> {
        return settingsDao.getTournamentSettings().map { it?.toDomain() }
    }

    override suspend fun updateTournamentSettings(settings: TournamentSettings): Result<Unit> {
        return try {
            settingsDao.updateSettings(settings.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun initializeDefaultSettings(): Result<Unit> {
        return try {
            val defaultSettings = TournamentSettingsEntity()
            settingsDao.insertOrUpdateSettings(defaultSettings)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun TournamentSettingsEntity.toDomain() = TournamentSettings(
        id = id,
        tournamentName = tournamentName,
        edition = edition,
        category = category,
        stadium = stadium,
        registrationFee = registrationFee,
        slogan = slogan,
        numberOfGroups = numberOfGroups,
        drawDate = drawDate,
        drawTime = drawTime,
        createdAt = createdAt
    )

    private fun TournamentSettings.toEntity() = TournamentSettingsEntity(
        id = id,
        tournamentName = tournamentName,
        edition = edition,
        category = category,
        stadium = stadium,
        registrationFee = registrationFee,
        slogan = slogan,
        numberOfGroups = numberOfGroups,
        drawDate = drawDate,
        drawTime = drawTime,
        createdAt = createdAt
    )
}
