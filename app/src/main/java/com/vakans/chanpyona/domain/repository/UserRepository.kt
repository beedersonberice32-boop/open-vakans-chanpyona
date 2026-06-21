package com.vakans.chanpyona.domain.repository

import com.vakans.chanpyona.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun loginUser(username: String, password: String): Result<User>
    suspend fun createUser(user: User): Result<Unit>
    fun getAllUsers(): Flow<List<User>>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun deleteUser(userId: Int): Result<Unit>
}
