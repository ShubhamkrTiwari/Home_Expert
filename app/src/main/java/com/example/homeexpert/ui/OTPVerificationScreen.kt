package com.example.homeexpert.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OTPVerificationScreen(navController: NavController, userRole: String?) {
    val otpLength = 4
    val otp = remember { mutableStateListOf(*Array(otpLength) { "" }) }
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = Icons.Default.CheckCircle, // Placeholder icon
                contentDescription = "OTP Sent",
                modifier = Modifier.size(120.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("OTP Verification", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Enter the OTP sent to +91 7091876884", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(48.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                (0 until otpLength).forEach { index ->
                    BasicTextField(
                        value = otp[index],
                        onValueChange = {
                            val newText = it.filter { char -> char.isDigit() }
                            if (newText.length <= 1) {
                                otp[index] = newText
                                if (newText.isNotEmpty() && index < otpLength - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesters[index])
                            .onKeyEvent {
                                if (it.type == KeyEventType.KeyUp && it.key == Key.Backspace && otp[index].isEmpty() && index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                    true
                                } else {
                                    false
                                }
                            }
                            .drawBehind {
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = 2.dp.toPx()
                                )
                            },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = {
                    val finalOtp = otp.joinToString("")
                    // TODO: Verify OTP
                    navController.navigate("profile_setup/$userRole")
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = otp.all { it.isNotEmpty() }
            ) {
                Text("Verify")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { /* Handle Resend OTP */ }) {
                Text("Didn't you receive the OTP? Resend OTP")
            }
        }
    }
}
