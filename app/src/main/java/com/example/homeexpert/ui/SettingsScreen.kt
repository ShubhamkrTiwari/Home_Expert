package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Settings") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Notifications", style = MaterialTheme.typography.titleMedium)
                    SettingItem(title = "Enable Push Notifications", checked = true)
                }
            }
            item { Divider() }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Appearance", style = MaterialTheme.typography.titleMedium)
                    SettingItem(title = "Enable Dark Mode", checked = false)
                }
            }
        }
    }
}

@Composable
fun SettingItem(title: String, checked: Boolean) {
    val isChecked = remember { mutableStateOf(checked) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.weight(1f))
        Switch(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
    }
}
