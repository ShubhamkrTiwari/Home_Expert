package com.example.homeexpert.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarningsSummaryScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Earnings Summary") }) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total Earnings", style = MaterialTheme.typography.titleMedium)
                        Text("$1,250", style = MaterialTheme.typography.headlineLarge)
                    }
                }
            }
            item {
                Text(
                    "Recent Transactions",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            val transactions = listOf(
                Transaction("Plumbing Service", "+$50"),
                Transaction("Electrical Repair", "+$75"),
                Transaction("Tutoring Session", "+$40")
            )
            items(transactions) {
                TransactionItem(it)
                Divider()
            }
        }
    }
}

data class Transaction(val title: String, val amount: String)

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.ArrowUpward, contentDescription = "Income", tint = Color(0xFF4CAF50), modifier = Modifier.padding(end = 16.dp))
        Column {
            Text(transaction.title, style = MaterialTheme.typography.bodyLarge)
            Text(transaction.amount, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
