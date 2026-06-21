package com.vakans.chanpyona.domain.usecase.match

import com.vakans.chanpyona.domain.model.Match
import com.vakans.chanpyona.domain.model.MatchStatus
import com.vakans.chanpyona.domain.repository.MatchRepository
import com.vakans.chanpyona.domain.repository.StandingRepository

class UpdateMatchScoreUseCase(
    private val matchRepository: MatchRepository,
    private val standingRepository: StandingRepository
) {
    suspend operator fun invoke(matchId: Int, team1Score: Int, team2Score: Int): Result<Unit> {
        if (team1Score < 0 || team2Score < 0) {
            return Result.failure(IllegalArgumentException("Scores cannot be negative"))
        }
        
        val result = matchRepository.updateMatchScore(matchId, team1Score, team2Score)
        if (result.isSuccess) {
            matchRepository.updateMatchStatus(matchId, MatchStatus.COMPLETED)
            standingRepository.calculateStandings(matchId)
        }
        return result
    }
}
