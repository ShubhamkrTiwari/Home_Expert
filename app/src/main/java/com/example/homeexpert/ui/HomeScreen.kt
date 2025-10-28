package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.repositories.HomeRepository

// Data class for bottom navigation items
data class CustomerBottomNavItem(val label: String, val icon: ImageVector, val route: String)

@Composable
fun CustomerBottomNavigation(navController: NavController) {
    val items = listOf(
        CustomerBottomNavItem("Home", Icons.Default.Home, "home"),
        CustomerBottomNavItem("Bookings", Icons.Default.ListAlt, "my_bookings"),
        CustomerBottomNavItem("Profile", Icons.Default.Person, "customer_profile")
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


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
                    IconButton(onClick = { navController.navigate("customer_notifications") }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = { CustomerBottomNavigation(navController = navController) },
        containerColor = Color.White // White background
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item { SearchBar(searchQuery) { searchQuery = it } }
            item { ServiceGrid(services, navController) }
            item {
                Text(
                    "Top Rated Professionals",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 8.dp),
                    color = Color.Black
                )
            }
            val filteredProfessionals = professionals.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.service.name.contains(searchQuery, ignoreCase = true)
            }
            items(filteredProfessionals) { professional ->
                ProfessionalCard(professional, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    navController.navigate("booking_screen/${professional.id}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    Box(modifier = Modifier.padding(16.dp)){
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {Text("Search for services or professionals")},
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}

@Composable
fun ServiceGrid(services: List<Service>, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("Services", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp), color = Color.Black)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            modifier = Modifier.height(220.dp), // Adjust height as needed
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(services) { service ->
                ServiceCard(service) {
                    navController.navigate("service_detail/${service.id}")
                }
            }
        }
    }
}

@Composable
fun ServiceCard(service: Service, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = service.icon), contentDescription = null, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(service.name, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ProfessionalCard(professional: Professional, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(modifier = modifier.fillMaxWidth().clickable(onClick = onClick), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = professional.service.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(professional.name, style = MaterialTheme.typography.titleMedium)
                Text(professional.service.name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                RatingBar(rating = professional.rating.toFloat())
            }
        }
    }
}

@Composable
fun RatingBar(rating: Float, maxRating: Int = 5) {
    Row {
        for (i in 1..maxRating) {
            val icon = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star
            val tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
