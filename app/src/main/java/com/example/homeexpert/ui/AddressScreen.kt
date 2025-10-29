package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.data.Address
import com.example.homeexpert.ui.components.AddressItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(navController: NavController) {
    val addresses = remember {
        listOf(
            Address("1", "Home", "123 Main St", "Anytown", "CA", "12345"),
            Address("2", "Work", "456 Market St", "Anytown", "CA", "12345")
        )
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Manage Address") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(addresses) { address ->
                    AddressItem(address = address)
                }
            }
            Button(onClick = { /* TODO: Navigate to add address screen */ }) {
                Text("Add New Address")
            }
        }
    }
}