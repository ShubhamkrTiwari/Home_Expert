package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.R

@Composable
fun ProfileSetupScreen(navController: NavController, userRole: String?) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_professional_placeholder),
                contentDescription = "Avatar",
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Complete Your Profile", style = MaterialTheme.typography.headlineSmall)
            Text("A few more details to get you started", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { 
                    val destination = if (userRole == "professional") "agent_dashboard" else "home"
                    navController.navigate(destination) {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save & Continue")
            }
        }
    }
}
