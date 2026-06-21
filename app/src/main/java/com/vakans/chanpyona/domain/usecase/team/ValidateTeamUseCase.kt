package com.vakans.chanpyona.domain.usecase.team

import com.vakans.chanpyona.domain.model.Team
import com.vakans.chanpyona.domain.repository.TeamRepository

class ValidateTeamUseCase(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(teamId: Int): Result<Unit> {
        return teamRepository.validateTeam(teamId)
    }
}
