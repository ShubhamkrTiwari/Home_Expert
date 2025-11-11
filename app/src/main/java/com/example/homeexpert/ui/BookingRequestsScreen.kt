package com.example.homeexpert.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.data.Booking
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.repositories.HomeRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingRequestsScreen(navController: NavController) {
    var bookings by remember { mutableStateOf(emptyList<Booking>()) }
    var professionals by remember { mutableStateOf(emptyList<Professional>()) }
    var services by remember { mutableStateOf(emptyList<Service>()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            bookings = HomeRepository.getBookings()
            professionals = HomeRepository.getProfessionals()
            services = HomeRepository.getServices()
        }
    }

    val bookingRequests = bookings.filter { it.status == "Confirmed" } // Example filter

    Scaffold(
        topBar = { TopAppBar(title = { Text("Booking Requests") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            items(bookingRequests) { booking ->
                val professional = professionals.find { it.id == booking.professionalId }
                val service = services.find { it.id == booking.serviceId }
                if (professional != null && service != null) {
                    BookingRequestCard(booking, professional, service, navController)
                }
            }
        }
    }
}

@Composable
fun BookingRequestCard(booking: Booking, professional: Professional, service: Service, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("job_details/${booking.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(service.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.padding(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = "Customer", modifier = Modifier.padding(end = 8.dp))
                Text("with ${professional.name}", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.DateRange, contentDescription = "Date & Time", modifier = Modifier.padding(end = 8.dp))
                Text("Date: ${booking.date}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.weight(1f))
                Text("Time: ${booking.time}", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Row {
                Button(
                    onClick = { /* Handle Accept */ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF49AF4C))
                ) {
                    Text("Accept", color = Color.White)
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                TextButton(onClick = { /* Handle Decline */ }, modifier = Modifier.weight(1f)) {
                    Text("Decline", color = Color(0xFFEA4A3E))
                }
            }
        }
    }
}
