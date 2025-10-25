package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(navController: NavController, bookingId: String?) {
    // In a real app, you would fetch the booking details using the bookingId
    Scaffold(
        topBar = { TopAppBar(title = { Text("Job Details") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Job Details for Booking #$bookingId", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    JobDetailItem(icon = Icons.Default.Person, title = "Customer", subtitle = "John Doe")
                    JobDetailItem(icon = Icons.Default.LocationOn, title = "Location", subtitle = "123, Main Street, Anytown")
                    JobDetailItem(icon = Icons.Default.DateRange, title = "Time", subtitle = "10:00 AM")
                }
            }
            item {
                // Placeholder for a map preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="Map preview" )
                }
            }
            item {
                Button(
                    onClick = { /* Handle job completion */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Mark as Completed")
                }
            }
        }
    }
}

@Composable
fun JobDetailItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(end = 16.dp))
        Column {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
