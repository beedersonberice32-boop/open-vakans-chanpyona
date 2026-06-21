package com.vakans.chanpyona.domain.usecase.auth

import com.vakans.chanpyona.domain.model.User
import com.vakans.chanpyona.domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        if (username.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Username and password cannot be empty"))
        }
        return userRepository.loginUser(username, password)
    }
}
