package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.homeexpert.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agent Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = R.drawable.paintericon),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(Modifier.height(16.dp))
            }
            item {
                Text("Jane Smith", style = MaterialTheme.typography.headlineSmall)
                Text("Plumber", style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(32.dp))
            }
            item {
                AgentProfileMenuItem(
                    icon = Icons.Default.AccountBalanceWallet,
                    text = "Earnings Summary"
                ) {
                    navController.navigate("earnings_summary")
                }
            }
            item {
                AgentProfileMenuItem(
                    icon = Icons.Default.Settings,
                    text = "Settings"
                ) {
                    navController.navigate("settings")
                }
            }
            item {
                AgentProfileMenuItem(
                    icon = Icons.Filled.ExitToApp,
                    text = "Logout"
                ) {
                    navController.navigate("welcome") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AgentProfileMenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = text)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, style = MaterialTheme.typography.bodyLarge)
        }
        Divider(modifier = Modifier.padding(horizontal = 16.dp))
    }
}
