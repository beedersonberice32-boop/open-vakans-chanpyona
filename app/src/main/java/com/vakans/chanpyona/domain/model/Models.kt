package com.vakans.chanpyona.domain.model

import java.time.LocalDateTime

data class TournamentSettings(
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

data class User(
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: UserRole,
    val email: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class UserRole {
    SUPER_ADMIN, ADMIN
}

data class Team(
    val id: Int = 0,
    val teamName: String,
    val commune: String,
    val responsible: String,
    val phone: String,
    val email: String,
    val status: TeamStatus = TeamStatus.PENDING,
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val paymentAmount: Int = 2500,
    val whatsappContact: String? = null,
    val groupId: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class TeamStatus {
    PENDING, VALIDATED, REJECTED
}

enum class PaymentStatus {
    PENDING, PAID
}

data class Player(
    val id: Int = 0,
    val teamId: Int,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val jerseyNumber: Int,
    val position: String,
    val height: Float? = null,
    val weight: Float? = null,
    val status: PlayerStatus = PlayerStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class PlayerStatus {
    PENDING, VALIDATED, REJECTED
}

data class Document(
    val id: Int = 0,
    val playerId: Int,
    val documentType: DocumentType,
    val filePath: String,
    val uploadedAt: LocalDateTime = LocalDateTime.now()
)

enum class DocumentType {
    BIRTH_CERTIFICATE, GRADE_9_SHEET
}

data class Group(
    val id: Int = 0,
    val name: String,
    val phase: Phase = Phase.GROUP,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class Phase {
    GROUP, SEMI_FINAL, FINAL
}

data class Match(
    val id: Int = 0,
    val groupId: Int,
    val team1Id: Int,
    val team2Id: Int,
    val scheduledDate: LocalDateTime,
    val status: MatchStatus = MatchStatus.SCHEDULED,
    val team1Score: Int? = null,
    val team2Score: Int? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class MatchStatus {
    SCHEDULED, ONGOING, COMPLETED, CANCELLED
}

data class Standing(
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
) {
    val goalDifference: Int
        get() = goalsFor - goalsAgainst
}

data class Notification(
    val id: Int = 0,
    val teamId: Int,
    val title: String,
    val message: String,
    val type: NotificationType,
    val relatedMatchId: Int? = null,
    val isRead: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class NotificationType {
    MATCH_TOMORROW, MATCH_3H, MATCH_1H, TIME_CHANGE, QUALIFIED, ELIMINATED
}
