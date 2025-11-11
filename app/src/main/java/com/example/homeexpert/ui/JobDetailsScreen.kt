package com.example.homeexpert.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(bookingId: String?, navController: NavHostController) {
    val context = LocalContext.current
    val location = "123, Main Street, Anytown"
    val gmmIntentUri = Uri.parse("geo:0,0?q=$location")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Job Details") }) },
        bottomBar = {
            SlideToConfirm(
                onConfirmed = { /* Handle job completion */ },
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Service Details Card
            Card(elevation = CardDefaults.cardElevation(2.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Plumbing Service", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Booking #$bookingId", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            // Customer Details Card
            Card(elevation = CardDefaults.cardElevation(2.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    JobDetailItem(icon = Icons.Default.Person, title = "Customer Name", subtitle = "John Doe")
                    JobDetailItem(icon = Icons.Default.LocationOn, title = "Location", subtitle = location)
                    JobDetailItem(icon = Icons.Default.DateRange, title = "Date & Time", subtitle = "Today, 10:00 AM")
                }
            }

            // Map Preview Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { context.startActivity(mapIntent) },
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray), // Placeholder for map
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Map, contentDescription = "Map Icon", tint = Color.White, modifier = Modifier.size(64.dp))
                }
            }

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = { /* TODO: Contact customer */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Call", modifier = Modifier.size(ButtonDefaults.IconSize))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Contact")
                }
                Button(
                    onClick = { context.startActivity(mapIntent) },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Navigation, contentDescription = "Navigate", modifier = Modifier.size(ButtonDefaults.IconSize))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Navigate")
                }
            }
        }
    }
}

@Composable
fun JobDetailItem(icon: ImageVector, title: String, subtitle: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(subtitle, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlideToConfirm(
    onConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val interactionSource = remember { MutableInteractionSource() }
    val completedColor = Color(0xFF4CAF50) // Green

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(
                androidx.compose.ui.graphics.lerp(
                    start = MaterialTheme.colorScheme.surfaceVariant,
                    stop = completedColor,
                    fraction = sliderPosition
                )
            )
    ) {
        Text(
            text = "Slide to Mark as Completed",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alpha((1f - sliderPosition * 2).coerceAtLeast(0f))
        )
        Text(
            text = "Completed",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alpha(((sliderPosition - 0.5f) * 2).coerceIn(0f, 1f))
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = {
                if (sliderPosition > 0.95f) {
                    onConfirmed()
                }
                // Reset slider position
                sliderPosition = 0f
            },
            modifier = Modifier.fillMaxWidth(),
            valueRange = 0f..1f,
            interactionSource = interactionSource,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
            ),
            thumb = {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (sliderPosition > 0.95f) Icons.Default.Check else Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Slide to complete",
                        tint = Color.White
                    )
                }
            }
        )
    }
}
