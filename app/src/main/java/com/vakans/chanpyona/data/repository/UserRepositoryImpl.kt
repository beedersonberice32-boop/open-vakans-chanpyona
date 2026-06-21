package com.vakans.chanpyona.data.repository

import com.vakans.chanpyona.data.local.dao.UserDao
import com.vakans.chanpyona.data.local.entity.UserEntity
import com.vakans.chanpyona.domain.model.User
import com.vakans.chanpyona.domain.model.UserRole
import com.vakans.chanpyona.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun loginUser(username: String, password: String): Result<User> {
        return try {
            val user = userDao.getUserByUsername(username)
            if (user != null && user.password == password) {
                Result.success(user.toDomain())
            } else {
                Result.failure(Exception("Invalid username or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createUser(user: User): Result<Unit> {
        return try {
            userDao.insertUser(user.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { users ->
            users.map { it.toDomain() }
        }
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        return try {
            userDao.updateUser(user.toEntity())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(userId: Int): Result<Unit> {
        return try {
            val user = userDao.getUserByUsername("") // Placeholder
            if (user != null) {
                userDao.deleteUser(user)
                Result.success(Unit)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun UserEntity.toDomain() = User(
        id = id,
        username = username,
        password = password,
        role = UserRole.valueOf(role),
        email = email,
        createdAt = createdAt
    )

    private fun User.toEntity() = UserEntity(
        id = id,
        username = username,
        password = password,
        role = role.name,
        email = email,
        createdAt = createdAt
    )
}
