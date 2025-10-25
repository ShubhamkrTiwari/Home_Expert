package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homeexpert.R

@Composable
fun OTPVerificationScreen(navController: NavController, userRole: String?) {
    var otp by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("OTP Verification", style = MaterialTheme.typography.headlineSmall)
            Text("Enter the OTP sent to your email", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(32.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (i in 0..5) {
                    OutlinedTextField(
                        value = otp.getOrNull(i)?.toString() ?: "",
                        onValueChange = { /* Handle OTP digit input */ },
                        modifier = Modifier.weight(1f),
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate("profile_setup/$userRole") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verify OTP")
            }
            TextButton(onClick = { /* Handle Resend OTP */ }) {
                Text("Didn't receive OTP? Resend")
            }
        }
    }
}
