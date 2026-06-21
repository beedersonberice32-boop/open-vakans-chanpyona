package com.vakans.chanpyona.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vakans.chanpyona.presentation.viewmodel.AdminViewModel

@Composable
fun AdminScreen(
    viewModel: AdminViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1a1a2e))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "TABLEAU DE BORD ADMINISTRATEUR",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6B6B)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0a0e27))
                .padding(paddingValues)
        ) {
            // Tab Navigation
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color(0xFF16213e),
                contentColor = Color.White
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("En Attente (${uiState.pendingTeams.size})") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Validées (${uiState.validatedTeams.size})") }
                )
                Tab(
                    selected = selectedTabIndex == 2,
                    onClick = { selectedTabIndex = 2 },
                    text = { Text("Rejetées (${uiState.rejectedTeams.size})") }
                )
            }

            // Content
            when (selectedTabIndex) {
                0 -> TeamsListContent(
                    teams = uiState.pendingTeams,
                    isLoading = uiState.isLoading,
                    onValidate = viewModel::validateTeam,
                    onReject = { teamId -> viewModel.rejectTeam(teamId, "Rejeté par admin") }
                )
                1 -> TeamsListContent(
                    teams = uiState.validatedTeams,
                    isLoading = uiState.isLoading,
                    showValidateButton = false
                )
                2 -> TeamsListContent(
                    teams = uiState.rejectedTeams,
                    isLoading = uiState.isLoading,
                    showValidateButton = false
                )
            }
        }
    }
}

@Composable
fun TeamsListContent(
    teams: List<com.vakans.chanpyona.domain.model.Team>,
    isLoading: Boolean,
    showValidateButton: Boolean = true,
    onValidate: (Int) -> Unit = {},
    onReject: (Int) -> Unit = {}
) {
    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Chargement...", color = Color.White)
        }
    } else if (teams.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Aucune équipe", color = Color(0xFFB0B0B0))
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(teams) { team ->
                TeamAdminCard(
                    team = team,
                    showValidateButton = showValidateButton,
                    onValidate = { onValidate(team.id) },
                    onReject = { onReject(team.id) }
                )
            }
        }
    }
}

@Composable
fun TeamAdminCard(
    team: com.vakans.chanpyona.domain.model.Team,
    showValidateButton: Boolean = true,
    onValidate: () -> Unit = {},
    onReject: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF16213e)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = team.teamName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Commune: ${team.commune}",
                fontSize = 12.sp,
                color = Color(0xFFB0B0B0),
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Responsable: ${team.responsible}",
                fontSize = 12.sp,
                color = Color(0xFFB0B0B0)
            )
            Text(
                text = "Tél: ${team.phone}",
                fontSize = 12.sp,
                color = Color(0xFFB0B0B0)
            )
            Text(
                text = "Email: ${team.email}",
                fontSize = 12.sp,
                color = Color(0xFFB0B0B0),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (showValidateButton) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onValidate,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4ECDC4)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Valider",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text("Valider")
                    }
                    Button(
                        onClick = onReject,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B6B)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Rejeter",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text("Rejeter")
                    }
                }
            }
        }
    }
}
