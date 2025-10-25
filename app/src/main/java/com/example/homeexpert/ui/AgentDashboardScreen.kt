package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentDashboardScreen(navController: NavController) {
    Scaffold(
        topBar = { 
            TopAppBar(
                title = { Text("Agent Dashboard") },
                actions = {
                    IconButton(onClick = { navController.navigate("notifications") }) {
                        Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                    }
                    IconButton(onClick = { navController.navigate("agent_profile") }) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                }
            ) 
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Handle FAB click */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            item { BookingRequestsSummary(navController) }
            item { UpcomingJobsSummary(navController) }
            item { EarningsSummary(navController) }
        }
    }
}

@Composable
fun BookingRequestsSummary(navController: NavController) {
    DashboardCard(
        title = "New Booking Requests",
        icon = Icons.Default.List,
        content = "You have 3 new requests",
        actionText = "View Requests",
        onClick = { navController.navigate("booking_requests") }
    )
}

@Composable
fun UpcomingJobsSummary(navController: NavController) {
    DashboardCard(
        title = "Upcoming Jobs",
        icon = Icons.Default.DateRange,
        content = "You have 2 jobs scheduled for today",
        actionText = "View Schedule",
        onClick = { /* Navigate to a schedule screen */ }
    )
}

@Composable
fun EarningsSummary(navController: NavController) {
    DashboardCard(
        title = "Earnings Summary",
        icon = Icons.Default.getAccountBalanceWallet(),
        content = "You've earned $500 this week",
        actionText = "View Earnings",
        onClick = { navController.navigate("earnings_summary") }
    )
}

private fun Icons.Filled.getAccountBalanceWallet(): ImageVector {
    TODO("Not yet implemented")
}

@Composable
fun DashboardCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, content: String, actionText: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, modifier = Modifier.padding(end = 16.dp))
                Text(title, style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.padding(8.dp))
            TextButton(onClick = onClick) {
                Text(actionText)
                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}
