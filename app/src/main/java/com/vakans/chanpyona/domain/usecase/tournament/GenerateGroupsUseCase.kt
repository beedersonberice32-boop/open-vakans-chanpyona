package com.vakans.chanpyona.domain.usecase.tournament

import com.vakans.chanpyona.domain.model.Group
import com.vakans.chanpyona.domain.repository.GroupRepository
import kotlin.random.Random

class GenerateGroupsUseCase(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(numberOfGroups: Int, teams: List<Int>): Result<List<Group>> {
        if (numberOfGroups <= 0 || teams.isEmpty()) {
            return Result.failure(IllegalArgumentException("Invalid number of groups or teams"))
        }
        return groupRepository.generateGroups(numberOfGroups, teams.size)
    }

    private fun shuffleTeamsIntoGroups(teams: List<Int>, numberOfGroups: Int): Map<Int, List<Int>> {
        val shuffled = teams.shuffled(Random(System.currentTimeMillis()))
        return shuffled.groupingBy { shuffled.indexOf(it) % numberOfGroups }.toMap()
    }
}
