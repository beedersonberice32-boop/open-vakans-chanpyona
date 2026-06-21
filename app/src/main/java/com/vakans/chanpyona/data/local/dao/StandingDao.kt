package com.vakans.chanpyona.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vakans.chanpyona.data.local.entity.StandingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StandingDao {
    @Query("SELECT * FROM standings WHERE groupId = :groupId ORDER BY rank ASC")
    fun getStandingsByGroup(groupId: Int): Flow<List<StandingEntity>>

    @Query("SELECT * FROM standings WHERE teamId = :teamId")
    fun getStandingByTeam(teamId: Int): Flow<StandingEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStanding(standing: StandingEntity)

    @Update
    suspend fun updateStanding(standing: StandingEntity)

    @Delete
    suspend fun deleteStanding(standing: StandingEntity)
}
