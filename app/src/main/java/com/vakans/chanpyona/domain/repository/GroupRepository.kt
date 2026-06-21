package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {
    fun getAllGroups(): Flow<List<Group>>
    fun getGroupById(groupId: Int): Flow<Group?>
    suspend fun createGroup(group: Group): Result<Unit>
    suspend fun generateGroups(numberOfGroups: Int, numberOfTeams: Int): Result<List<Group>>
    suspend fun deleteGroup(groupId: Int): Result<Unit>
}
