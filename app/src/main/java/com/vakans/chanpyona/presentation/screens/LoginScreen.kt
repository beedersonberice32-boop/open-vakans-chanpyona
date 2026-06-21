package com.vakans.chanpyona.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vakans.chanpyona.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0a0e27))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header
        Text(
            text = "OPEN VAKANS",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF6B6B)
        )
        Text(
            text = "CHANPYONA",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4ECDC4),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Subtitle
        Text(
            text = "Connexion",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Username Field
        OutlinedTextField(
            value = uiState.username,
            onValueChange = viewModel::updateUsername,
            label = { Text("Nom d'utilisateur") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4ECDC4),
                unfocusedBorderColor = Color(0xFF16213e),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color(0xFF4ECDC4),
                unfocusedLabelColor = Color(0xFFB0B0B0)
            )
        )

        // Password Field
        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::updatePassword,
            label = { Text("Mot de passe") },
            type = PasswordVisualTransformation(),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4ECDC4),
                unfocusedBorderColor = Color(0xFF16213e),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color(0xFF4ECDC4),
                unfocusedLabelColor = Color(0xFFB0B0B0)
            )
        )

        // Error Message
        if (!uiState.errorMessage.isNullOrEmpty()) {
            Text(
                text = uiState.errorMessage,
                color = Color(0xFFFF6B6B),
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )
        }

        // Login Button
        Button(
            onClick = {
                viewModel.login()
                if (uiState.isSuccess) {
                    onLoginSuccess()
                }
            },
            enabled = !uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF6B6B),
                disabledContainerColor = Color(0xFF4ECDC4)
            )
        ) {
            Text(
                text = if (uiState.isLoading) "Connexion..." else "Se Connecter",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Register Link
        Text(
            text = "Inscrire une équipe",
            color = Color(0xFF4ECDC4),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}
