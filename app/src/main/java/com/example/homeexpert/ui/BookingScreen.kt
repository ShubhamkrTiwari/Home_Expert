package com.example.homeexpert.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.data.Booking
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.repositories.HomeRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController, professionalId: String?) {
    var professional by remember { mutableStateOf<Professional?>(null) }
    var service by remember { mutableStateOf<Service?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(professionalId) {
        if (professionalId != null) {
            coroutineScope.launch {
                val prof = HomeRepository.getProfessionals().find { it.id == professionalId }
                professional = prof
                if (prof != null) {
                    service = HomeRepository.getServices().find { it.id == prof.serviceId }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book a Service") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = Color(0xFFF4F4F4) // Light gray background
    ) { innerPadding ->
        val currentProfessional = professional
        val currentService = service
        if (currentProfessional != null && currentService != null) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ProfessionalBookingHeader(currentProfessional, currentService)
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
                item {
                    BookingForm(
                        professional = currentProfessional,
                        service = currentService,
                        onConfirm = { navController.navigate("booking_success") }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading details...") // Or a CircularProgressIndicator
            }
        }
    }
}

@Composable
fun ProfessionalBookingHeader(professional: Professional, service: Service) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(id = getIconForService(service.name)),
            contentDescription = "Service Icon",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Text("You are booking", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(professional.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(service.name, style = MaterialTheme.typography.titleLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingForm(professional: Professional, service: Service, onConfirm: () -> Unit) {
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val dateState = rememberDatePickerState()
    val timeState = rememberTimePickerState()

    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    val timeFormatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        dateState.selectedDateMillis?.let { millis ->
                            val cal = Calendar.getInstance()
                            cal.timeInMillis = millis
                            date = dateFormatter.format(cal.time)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            modifier = Modifier.fillMaxWidth(),
            title = { Text("Select Time") },
            text = {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    TimePicker(state = timeState)
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, timeState.hour)
                        cal.set(Calendar.MINUTE, timeState.minute)
                        time = timeFormatter.format(cal.time)
                        showTimePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showTimePicker = false }) { Text("Cancel") }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) { 
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                "Select a date and time",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Box(modifier = Modifier.clickable { showDatePicker = true }) {
                OutlinedTextField(
                    value = date,
                    onValueChange = { },
                    label = { Text("Date") },
                    placeholder = { Text("e.g., 25/12/2024") },
                    leadingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.clickable { showTimePicker = true }) {
                OutlinedTextField(
                    value = time,
                    onValueChange = { },
                    label = { Text("Time") },
                    placeholder = { Text("e.g., 10:00 AM") },
                    leadingIcon = { Icon(Icons.Default.Schedule, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                     coroutineScope.launch {
                        val newBooking = Booking(
                            id = UUID.randomUUID().toString(),
                            professionalId = professional.id,
                            serviceId = service.id,
                            date = date,
                            time = time,
                            status = "Confirmed"
                        )
                        HomeRepository.addBooking(newBooking)
                        onConfirm()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Confirm Booking", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
