package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.Standing
import kotlinx.coroutines.flow.Flow

interface StandingRepository {
    fun getStandingsByGroup(groupId: Int): Flow<List<Standing>>
    fun getStandingByTeam(teamId: Int): Flow<Standing?>
    suspend fun updateStanding(standing: Standing): Result<Unit>
    suspend fun calculateStandings(groupId: Int): Result<Unit>
}
