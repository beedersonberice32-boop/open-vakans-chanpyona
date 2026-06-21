package com.vakans.chanpyona.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vakans.chanpyona.data.local.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM matches")
    fun getAllMatches(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE groupId = :groupId")
    fun getMatchesByGroup(groupId: Int): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE id = :matchId")
    fun getMatchById(matchId: Int): Flow<MatchEntity?>

    @Query("SELECT * FROM matches WHERE status = :status")
    fun getMatchesByStatus(status: String): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE DATE(scheduledDate) = DATE('now')")
    fun getMatchesToday(): Flow<List<MatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: MatchEntity)

    @Update
    suspend fun updateMatch(match: MatchEntity)

    @Delete
    suspend fun deleteMatch(match: MatchEntity)
}
