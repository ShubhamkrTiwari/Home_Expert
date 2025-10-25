package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.data.Booking
import com.example.homeexpert.data.repositories.HomeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingsScreen(navController: NavController) {
    val homeRepository = HomeRepository()
    val bookings = homeRepository.getBookings()
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Upcoming", "Completed", "Cancelled")

    val filteredBookings = remember(tabIndex, bookings) {
        when (tabs[tabIndex]) {
            "Upcoming" -> bookings.filter { it.status == "Confirmed" }
            "Completed" -> bookings.filter { it.status == "Completed" }
            "Cancelled" -> bookings.filter { it.status == "Cancelled" }
            else -> emptyList()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Bookings") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PrimaryTabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(filteredBookings) {
                    BookingCard(it)
                }
            }
        }
    }
}

@Composable
fun BookingCard(booking: Booking) {
    val statusColor = when (booking.status) {
        "Confirmed" -> Color(0xFF4CAF50) // Green
        "Completed" -> MaterialTheme.colorScheme.primary
        "Cancelled" -> Color(0xFFF44336) // Red
        else -> MaterialTheme.colorScheme.onSurface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(booking.service.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.padding(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = "Professional", modifier = Modifier.padding(end = 8.dp))
                Text("with ${booking.professional.name}", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DateRange, contentDescription = "Date & Time", modifier = Modifier.padding(end = 8.dp))
                Text("Date: ${booking.date}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.weight(1f))
                Text("Time: ${booking.time}", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                 Icon(Icons.Default.Done, contentDescription = "Status", modifier = Modifier.padding(end = 8.dp))
                 Text("Status: ${booking.status}", style = MaterialTheme.typography.bodyMedium, color = statusColor)
            }
        }
    }
}
