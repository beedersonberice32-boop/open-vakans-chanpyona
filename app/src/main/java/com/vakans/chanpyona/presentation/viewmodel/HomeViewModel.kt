package com.vakans.chanpyona.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vakans.chanpyona.domain.model.TournamentSettings
import com.vakans.chanpyona.domain.repository.TournamentSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tournamentSettingsRepository: TournamentSettingsRepository
) : ViewModel() {

    private val _tournamentSettings = MutableStateFlow<TournamentSettings?>(null)
    val tournamentSettings: StateFlow<TournamentSettings?> = _tournamentSettings.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTournamentSettings()
    }

    private fun loadTournamentSettings() {
        viewModelScope.launch {
            tournamentSettingsRepository.getTournamentSettings().collect { settings ->
                _tournamentSettings.value = settings
                _isLoading.value = false
            }
        }
    }
}
