package com.vakans.chanpyona.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "tournament_settings")
data class TournamentSettingsEntity(
    @PrimaryKey
    val id: Int = 1,
    val tournamentName: String = "OPEN VAKANS CHANPYONA",
    val edition: String = "2ème Édition",
    val category: String = "U16",
    val stadium: String = "Bolobolo",
    val registrationFee: Int = 2500,
    val slogan: String = "Discipline - Transparence - Développement des jeunes talents",
    val numberOfGroups: Int = 0,
    val drawDate: LocalDateTime? = null,
    val drawTime: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String, // SUPER_ADMIN, ADMIN
    val email: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamName: String,
    val commune: String,
    val responsible: String,
    val phone: String,
    val email: String,
    val status: String, // PENDING, VALIDATED, REJECTED
    val paymentStatus: String, // PENDING, PAID
    val paymentAmount: Int = 2500,
    val whatsappContact: String? = null,
    val groupId: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamId: Int,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val jerseyNumber: Int,
    val position: String,
    val height: Float? = null,
    val weight: Float? = null,
    val status: String, // PENDING, VALIDATED, REJECTED
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerId: Int,
    val documentType: String, // BIRTH_CERTIFICATE, GRADE_9_SHEET
    val filePath: String,
    val uploadedAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phase: String, // GROUP, SEMI_FINAL, FINAL
    val createdAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val groupId: Int,
    val team1Id: Int,
    val team2Id: Int,
    val scheduledDate: LocalDateTime,
    val status: String, // SCHEDULED, ONGOING, COMPLETED, CANCELLED
    val team1Score: Int? = null,
    val team2Score: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "standings")
data class StandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val groupId: Int,
    val teamId: Int,
    val played: Int = 0,
    val wins: Int = 0,
    val draws: Int = 0,
    val losses: Int = 0,
    val goalsFor: Int = 0,
    val goalsAgainst: Int = 0,
    val points: Int = 0,
    val rank: Int = 0
)

@Entity(tableName = "scorers")
data class ScorerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerId: Int,
    val teamId: Int,
    val matchId: Int,
    val goals: Int = 1,
    val totalGoals: Int = 0
)

@Entity(tableName = "yellow_cards")
data class YellowCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerId: Int,
    val matchId: Int,
    val minute: Int,
    val issuedAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "red_cards")
data class RedCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerId: Int,
    val matchId: Int,
    val minute: Int,
    val issuedAt: LocalDateTime = LocalDateTime.now()
)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teamId: Int,
    val title: String,
    val message: String,
    val type: String, // MATCH_TOMORROW, MATCH_3H, MATCH_1H, TIME_CHANGE, QUALIFIED, ELIMINATED
    val relatedMatchId: Int? = null,
    val isRead: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
