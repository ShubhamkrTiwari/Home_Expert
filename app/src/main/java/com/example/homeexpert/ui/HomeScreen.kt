package com.example.homeexpert.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.repositories.HomeRepository
import com.example.homeexpert.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val homeRepository = HomeRepository()
    val services = homeRepository.getServices()
    val professionals = homeRepository.getProfessionals()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Expert") },
                actions = {
                    IconButton(onClick = { navController.navigate("map_view") }) {
                        Icon(Icons.Filled.Map, contentDescription = "Map")
                    }
                    TextButton(onClick = { navController.navigate("my_bookings") }) {
                        Text("My Bookings")
                    }
                    IconButton(onClick = { navController.navigate("customer_profile") }) {
                        Icon(Icons.Filled.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item { SearchBar(searchQuery) { searchQuery = it } }
            item { ServiceGrid(services, navController) }
            item { 
                Text(
                    "Top Rated Professionals", 
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                )
            }
            val filteredProfessionals = professionals.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.service.name.contains(searchQuery, ignoreCase = true)
            }
            items(filteredProfessionals) { professional ->
                ProfessionalCard(professional)
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    Box(modifier = Modifier.padding(16.dp)){
        OutlinedTextField(
            value = query, 
            onValueChange = onQueryChange,
            placeholder = {Text("Search for services or professionals")},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun ServiceGrid(services: List<Service>, navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Services", style = MaterialTheme.typography.titleMedium)
        LazyVerticalGrid(columns = GridCells.Fixed(4), modifier = Modifier.height(200.dp)) {
            items(services) {
                Column(
                    modifier = Modifier.padding(8.dp).clickable { navController.navigate("service_detail/${it.id}") }
                ) {
                    Icon(painter = painterResource(id = it.icon), contentDescription = null, modifier = Modifier.size(48.dp))
                    Text(it.name)
                }
            }
        }
    }
}

@Composable
fun ProfessionalCard(professional: Professional) {
    Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_professional_placeholder), contentDescription = null, modifier = Modifier.size(64.dp))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(professional.name, style = MaterialTheme.typography.titleMedium)
                Text(professional.service.name, style = MaterialTheme.typography.bodyMedium)
                Text("Rating: ${professional.rating}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
