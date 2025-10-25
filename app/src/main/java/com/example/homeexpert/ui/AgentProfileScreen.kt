package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowBack
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

@Composable
fun AgentProfileScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_professional_placeholder),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(MaterialTheme.shapes.medium),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            item {
                Text("Jane Smith", style = MaterialTheme.typography.headlineSmall)
                Text("Plumber", style = MaterialTheme.typography.bodyLarge)
            }
            item {
                Icons.Default.AccountBalanceWallet
                {
                    navController.navigate("earnings_summary")
                }
            }
            item {
                Icons.Default.Settings
                {
                    navController.navigate("settings")
                }
            }

            item {
                Icons.Default.ArrowBack
                navController.navigate("welcome") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }

                }
            }


        }
    }

}


