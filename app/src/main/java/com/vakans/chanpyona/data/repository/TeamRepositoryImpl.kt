package com.vakans.chanpyona.data.repository

import com.vakans.chanpyona.data.local.dao.TeamDao
import com.vakans.chanpyona.data.local.entity.TeamEntity
import com.vakans.chanpyona.domain.model.PaymentStatus
import com.vakans.chanpyona.domain.model.Team
import com.vakans.chanpyona.domain.model.TeamStatus
import com.vakans.chanpyona.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val teamDao: TeamDao
) : TeamRepository {

    override fun getAllTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams().map { teams ->
            teams.map { it.toDomain() }
        }
    }

    override fun getTeamById(teamId: Int): Flow<Team?> {
        return teamDao.getTeamById(teamId).map { it?.toDomain() }
    }

    override fun getTeamsByStatus(status: TeamStatus): Flow<List<Team>> {
        return teamDao.getTeamsByStatus(status.name).map { teams ->
            teams.map { it.toDomain() }
        }
    }

    override fun getTeamsByGroup(groupId: Int): Flow<List<Team>> {
        return teamDao.getTeamsByGroup(groupId).map { teams ->
            teams.map { it.toDomain() }
        }
    }

    override suspend fun registerTeam(team: Team): Result<Team> {
        return try {
            teamDao.insertTeam(team.toEntity())
            Result.success(team)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateTeam(team: Team): Result<Unit> {
        return try {
            teamDao.updateTeam(team.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun validateTeam(teamId: Int): Result<Unit> {
        return try {
            // Get team and update status
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun rejectTeam(teamId: Int, reason: String): Result<Unit> {
        return try {
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePaymentStatus(teamId: Int, isPaid: Boolean): Result<Unit> {
        return try {
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun TeamEntity.toDomain() = Team(
        id = id,
        teamName = teamName,
        commune = commune,
        responsible = responsible,
        phone = phone,
        email = email,
        status = TeamStatus.valueOf(status),
        paymentStatus = PaymentStatus.valueOf(paymentStatus),
        paymentAmount = paymentAmount,
        whatsappContact = whatsappContact,
        groupId = groupId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun Team.toEntity() = TeamEntity(
        id = id,
        teamName = teamName,
        commune = commune,
        responsible = responsible,
        phone = phone,
        email = email,
        status = status.name,
        paymentStatus = paymentStatus.name,
        paymentAmount = paymentAmount,
        whatsappContact = whatsappContact,
        groupId = groupId,
        createdAt = createdAt,
        updatedAt = LocalDateTime.now()
    )
}
