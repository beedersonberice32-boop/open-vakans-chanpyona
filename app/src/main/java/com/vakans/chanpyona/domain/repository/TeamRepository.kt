package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.Team
import com.vakans.chanpyona.domain.model.TeamStatus
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getAllTeams(): Flow<List<Team>>
    fun getTeamById(teamId: Int): Flow<Team?>
    fun getTeamsByStatus(status: TeamStatus): Flow<List<Team>>
    fun getTeamsByGroup(groupId: Int): Flow<List<Team>>
    suspend fun registerTeam(team: Team): Result<Team>
    suspend fun updateTeam(team: Team): Result<Unit>
    suspend fun validateTeam(teamId: Int): Result<Unit>
    suspend fun rejectTeam(teamId: Int, reason: String): Result<Unit>
    suspend fun updatePaymentStatus(teamId: Int, isPaid: Boolean): Result<Unit>
}
