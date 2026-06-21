package com.vakans.chanpyona.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vakans.chanpyona.presentation.components.TournamentHeaderCard
import com.vakans.chanpyona.presentation.components.QuickActionCard
import com.vakans.chanpyona.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToMatches: () -> Unit = {},
    onNavigateToTeams: () -> Unit = {},
    onNavigateToAdmin: () -> Unit = {}
) {
    val tournamentSettings by viewModel.tournamentSettings.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0f3460),
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
                    label = { Text("Accueil") },
                    selected = true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Info, contentDescription = "Matches") },
                    label = { Text("Matches") },
                    selected = false,
                    onClick = onNavigateToMatches
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Admin") },
                    label = { Text("Admin") },
                    selected = false,
                    onClick = onNavigateToAdmin
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0a0e27))
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (isLoading) {
                Text(
                    text = "Chargement...",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            } else if (tournamentSettings != null) {
                val settings = tournamentSettings!!
                
                TournamentHeaderCard(
                    tournamentName = settings.tournamentName,
                    edition = settings.edition,
                    stadium = settings.stadium,
                    category = settings.category,
                    registrationFee = settings.registrationFee,
                    slogan = settings.slogan
                )

                // Quick Actions Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Actions Rapides",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    QuickActionCard(
                        title = "📋 Matchs Aujourd'hui",
                        description = "Consultez les matchs du jour",
                        icon = "🏟",
                        backgroundColor = Color(0xFF16213e),
                        onClick = onNavigateToMatches
                    )

                    QuickActionCard(
                        title = "👥 Équipes",
                        description = "Voir toutes les équipes inscrites",
                        icon = "⚽",
                        backgroundColor = Color(0xFF0f3460),
                        onClick = onNavigateToTeams
                    )

                    QuickActionCard(
                        title = "📊 Classement",
                        description = "Consultez le classement général",
                        icon = "🥇",
                        backgroundColor = Color(0xFF16213e)
                    )

                    QuickActionCard(
                        title = "🎲 Tirage au Sort",
                        description = "Résultats du tirage des groupes",
                        icon = "🎯",
                        backgroundColor = Color(0xFF0f3460)
                    )
                }

                // Information Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = "ℹ️ Information",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Restez à jour avec toutes les notifications de l'événement. Recevez des alertes pour les matchs à venir, les changements d'horaire et les résultats.",
                        fontSize = 12.sp,
                        color = Color(0xFFB0B0B0),
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}
