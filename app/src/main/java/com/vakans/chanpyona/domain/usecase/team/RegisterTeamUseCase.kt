package com.vakans.chanpyona.domain.usecase.team

import com.vakans.chanpyona.domain.model.Team
import com.vakans.chanpyona.domain.repository.TeamRepository

class RegisterTeamUseCase(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(team: Team): Result<Team> {
        if (team.teamName.isBlank() || team.email.isBlank() || team.phone.isBlank()) {
            return Result.failure(IllegalArgumentException("Team name, email, and phone are required"))
        }
        return teamRepository.registerTeam(team)
    }
}
