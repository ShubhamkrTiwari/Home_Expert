package com.example.homeexpert.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BookingSuccessScreen(navController: NavController) {
    Scaffold {
        innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Booking Successful!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))
            Text("Your appointment has been confirmed.")
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            } }) {
                Text("Back to Home")
            }
        }
    }
}
