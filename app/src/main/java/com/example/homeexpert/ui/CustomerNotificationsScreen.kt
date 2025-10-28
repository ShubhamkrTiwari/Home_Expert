package com.example.homeexpert.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerNotificationsScreen(navController: NavController) {
    val notifications = listOf(
        Notification(
            id = "1",
            title = "Booking Confirmed",
            subtitle = "Your booking with John Doe (Electrician) is confirmed for tomorrow at 10 AM.",
            timestamp = "2 hours ago",
            icon = Icons.Default.EventAvailable,
            isRead = false
        ),
        Notification(
            id = "2",
            title = "Payment Successful",
            subtitle = "You have successfully paid INR 500 for the plumbing service.",
            timestamp = "1 day ago",
            icon = Icons.Default.Payment,
            isRead = true
        ),
        Notification(
            id = "3",
            title = "New Service Available",
            subtitle = "We have launched a new car cleaning service in your area.",
            timestamp = "3 days ago",
            icon = Icons.Default.Info,
            isRead = true
        )
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Notifications") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(notifications) { notification ->
                NotificationItem(notification = notification)
            }
        }
    }
}