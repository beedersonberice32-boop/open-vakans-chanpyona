package com.vakans.chanpyona.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vakans.chanpyona.data.local.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players WHERE teamId = :teamId")
    fun getPlayersByTeam(teamId: Int): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM players WHERE id = :playerId")
    fun getPlayerById(playerId: Int): Flow<PlayerEntity?>

    @Query("SELECT * FROM players WHERE status = :status")
    fun getPlayersByStatus(status: String): Flow<List<PlayerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)

    @Update
    suspend fun updatePlayer(player: PlayerEntity)

    @Delete
    suspend fun deletePlayer(player: PlayerEntity)
}
