package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.R
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.repositories.HomeRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailScreen(navController: NavController, serviceId: String?) {
    val homeRepository = HomeRepository()
    val service = homeRepository.getServices().find { it.id == serviceId }
    val professionals = homeRepository.getProfessionals().filter { it.service.id == serviceId }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(service?.name ?: "Service Details") })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (service != null) {
                // Display service details and professionals
                Text(service.name, style = MaterialTheme.typography.headlineMedium)
                AllProfessionals(professionals, navController)
            } else {
                Text("Service not found")
            }
        }
    }
}

@Composable
fun AllProfessionals(professionals: List<Professional>, navController: NavController) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(professionals) {
            ProfessionalCard(it, navController)
        }
    }
}

@Composable
fun ProfessionalCard(professional: Professional, navController: NavController) {
    Card(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_professional_placeholder), contentDescription = null, modifier = Modifier.size(64.dp))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(professional.name, style = MaterialTheme.typography.titleMedium)
                Text(professional.service.name, style = MaterialTheme.typography.bodyMedium)
                Text("Rating: ${professional.rating}", style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate("booking_screen/${professional.id}") }) {
                Text("Book Now")
            }
        }
    }
}
