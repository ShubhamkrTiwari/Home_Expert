package com.example.homeexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.homeexpert.ui.AppNavigation
import com.example.homeexpert.ui.SettingsViewModel
import com.example.homeexpert.ui.theme.HomeExpertTheme

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()
            HomeExpertTheme(darkTheme = isDarkTheme) {
                AppNavigation()
            }
        }
    }
}
