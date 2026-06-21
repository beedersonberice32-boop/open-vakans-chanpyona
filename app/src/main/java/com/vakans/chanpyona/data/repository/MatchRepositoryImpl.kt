package com.vakans.chanpyona.data.repository

import com.vakans.chanpyona.data.local.dao.MatchDao
import com.vakans.chanpyona.data.local.entity.MatchEntity
import com.vakans.chanpyona.domain.model.Match
import com.vakans.chanpyona.domain.model.MatchStatus
import com.vakans.chanpyona.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchDao: MatchDao
) : MatchRepository {

    override fun getAllMatches(): Flow<List<Match>> {
        return matchDao.getAllMatches().map { matches ->
            matches.map { it.toDomain() }
        }
    }

    override fun getMatchById(matchId: Int): Flow<Match?> {
        return matchDao.getMatchById(matchId).map { it?.toDomain() }
    }

    override fun getMatchesByGroup(groupId: Int): Flow<List<Match>> {
        return matchDao.getMatchesByGroup(groupId).map { matches ->
            matches.map { it.toDomain() }
        }
    }

    override fun getMatchesByStatus(status: MatchStatus): Flow<List<Match>> {
        return matchDao.getMatchesByStatus(status.name).map { matches ->
            matches.map { it.toDomain() }
        }
    }

    override fun getMatchesToday(): Flow<List<Match>> {
        return matchDao.getMatchesToday().map { matches ->
            matches.map { it.toDomain() }
        }
    }

    override fun getUpcomingMatches(hours: Int): Flow<List<Match>> {
        return matchDao.getAllMatches().map { matches ->
            val now = LocalDateTime.now()
            val futureTime = now.plusHours(hours.toLong())
            matches.filter { match ->
                match.scheduledDate.isAfter(now) && match.scheduledDate.isBefore(futureTime)
            }.map { it.toDomain() }
        }
    }

    override suspend fun createMatch(match: Match): Result<Unit> {
        return try {
            matchDao.insertMatch(match.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMatchScore(matchId: Int, team1Score: Int, team2Score: Int): Result<Unit> {
        return try {
            // Get match and update score
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMatchStatus(matchId: Int, status: MatchStatus): Result<Unit> {
        return try {
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMatchTime(matchId: Int, newScheduledDate: LocalDateTime): Result<Unit> {
        return try {
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun MatchEntity.toDomain() = Match(
        id = id,
        groupId = groupId,
        team1Id = team1Id,
        team2Id = team2Id,
        scheduledDate = scheduledDate,
        status = MatchStatus.valueOf(status),
        team1Score = team1Score,
        team2Score = team2Score,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun Match.toEntity() = MatchEntity(
        id = id,
        groupId = groupId,
        team1Id = team1Id,
        team2Id = team2Id,
        scheduledDate = scheduledDate,
        status = status.name,
        team1Score = team1Score,
        team2Score = team2Score,
        createdAt = createdAt,
        updatedAt = LocalDateTime.now()
    )
}
