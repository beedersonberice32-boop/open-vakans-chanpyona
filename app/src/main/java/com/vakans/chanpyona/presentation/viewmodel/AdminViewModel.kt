package com.vakans.chanpyona.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vakans.chanpyona.domain.model.Team
import com.vakans.chanpyona.domain.model.TeamStatus
import com.vakans.chanpyona.domain.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AdminUiState(
    val allTeams: List<Team> = emptyList(),
    val pendingTeams: List<Team> = emptyList(),
    val validatedTeams: List<Team> = emptyList(),
    val rejectedTeams: List<Team> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTeamId: Int? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        loadTeams()
    }

    private fun loadTeams() {
        viewModelScope.launch {
            teamRepository.getAllTeams().collect { allTeams ->
                val pending = allTeams.filter { it.status == TeamStatus.PENDING }
                val validated = allTeams.filter { it.status == TeamStatus.VALIDATED }
                val rejected = allTeams.filter { it.status == TeamStatus.REJECTED }

                _uiState.value = AdminUiState(
                    allTeams = allTeams,
                    pendingTeams = pending,
                    validatedTeams = validated,
                    rejectedTeams = rejected,
                    isLoading = false
                )
            }
        }
    }

    fun selectTeam(teamId: Int) {
        _uiState.value = _uiState.value.copy(selectedTeamId = teamId)
    }

    fun validateTeam(teamId: Int) {
        viewModelScope.launch {
            val result = teamRepository.validateTeam(teamId)
            result.onSuccess {
                loadTeams()
            }
            result.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = error.message ?: "Erreur de validation"
                )
            }
        }
    }

    fun rejectTeam(teamId: Int, reason: String) {
        viewModelScope.launch {
            val result = teamRepository.rejectTeam(teamId, reason)
            result.onSuccess {
                loadTeams()
            }
            result.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = error.message ?: "Erreur de rejet"
                )
            }
        }
    }

    fun updatePaymentStatus(teamId: Int, isPaid: Boolean) {
        viewModelScope.launch {
            val result = teamRepository.updatePaymentStatus(teamId, isPaid)
            result.onSuccess {
                loadTeams()
            }
            result.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = error.message ?: "Erreur de mise à jour"
                )
            }
        }
    }
}
