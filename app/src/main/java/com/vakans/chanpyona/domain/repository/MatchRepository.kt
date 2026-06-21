package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.Match
import com.vakans.chanpyona.domain.model.MatchStatus
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface MatchRepository {
    fun getAllMatches(): Flow<List<Match>>
    fun getMatchById(matchId: Int): Flow<Match?>
    fun getMatchesByGroup(groupId: Int): Flow<List<Match>>
    fun getMatchesByStatus(status: MatchStatus): Flow<List<Match>>
    fun getMatchesToday(): Flow<List<Match>>
    fun getUpcomingMatches(hours: Int): Flow<List<Match>>
    suspend fun createMatch(match: Match): Result<Unit>
    suspend fun updateMatchScore(matchId: Int, team1Score: Int, team2Score: Int): Result<Unit>
    suspend fun updateMatchStatus(matchId: Int, status: MatchStatus): Result<Unit>
    suspend fun updateMatchTime(matchId: Int, newScheduledDate: LocalDateTime): Result<Unit>
}
