package com.vakans.chanpyona.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vakans.chanpyona.data.local.entity.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    fun getTeamById(teamId: Int): Flow<TeamEntity?>

    @Query("SELECT * FROM teams WHERE status = :status")
    fun getTeamsByStatus(status: String): Flow<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE groupId = :groupId")
    fun getTeamsByGroup(groupId: Int): Flow<List<TeamEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: TeamEntity)

    @Update
    suspend fun updateTeam(team: TeamEntity)

    @Delete
    suspend fun deleteTeam(team: TeamEntity)
}
