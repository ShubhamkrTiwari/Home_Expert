package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Notifications") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            // In a real app, you would have a list of notifications here
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("New Booking Request")
                    Text("You have a new booking request for a plumber.")
                }
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Booking Confirmed")
                    Text("Your booking for an electrician has been confirmed.")
                }
            }
        }
    }
}
