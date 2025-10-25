package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun CustomerProfileScreen(navController: NavController) {
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
                Text("John Doe", style = MaterialTheme.typography.headlineSmall)
                Text("123, Main Street, Anytown", style = MaterialTheme.typography.bodyLarge)
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item { ProfileMenuItem(icon = Icons.Default.List, text = "My Bookings") { navController.navigate("my_bookings") } }
            item { ProfileMenuItem(icon = Icons.Default.Settings, text = "Settings") { navController.navigate("settings") } }
            item { ProfileMenuItem(icon = Icons.Default.ExitToApp, text = "Logout") {
                navController.navigate("welcome") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            } }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}
