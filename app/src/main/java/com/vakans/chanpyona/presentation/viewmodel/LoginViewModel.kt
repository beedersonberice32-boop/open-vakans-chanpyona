package com.vakans.chanpyona.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vakans.chanpyona.domain.model.UserRole
import com.vakans.chanpyona.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
    val userRole: UserRole? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username, errorMessage = null)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password, errorMessage = null)
    }

    fun login() {
        val currentState = _uiState.value
        if (currentState.username.isBlank() || currentState.password.isBlank()) {
            _uiState.value = currentState.copy(
                errorMessage = "Veuillez remplir tous les champs"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = currentState.copy(isLoading = true)
            val result = loginUseCase(currentState.username, currentState.password)
            result.onSuccess { user ->
                _uiState.value = currentState.copy(
                    isLoading = false,
                    isSuccess = true,
                    userRole = user.role
                )
            }
            result.onFailure { error ->
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = error.message ?: "Erreur d'authentification"
                )
            }
        }
    }
}
