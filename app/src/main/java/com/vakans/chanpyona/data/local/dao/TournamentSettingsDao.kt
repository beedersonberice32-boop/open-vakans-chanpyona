package com.vakans.chanpyona.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vakans.chanpyona.data.local.entity.TournamentSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TournamentSettingsDao {
    @Query("SELECT * FROM tournament_settings LIMIT 1")
    fun getTournamentSettings(): Flow<TournamentSettingsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSettings(settings: TournamentSettingsEntity)

    @Update
    suspend fun updateSettings(settings: TournamentSettingsEntity)

    @Query("DELETE FROM tournament_settings")
    suspend fun deleteSettings()
}
