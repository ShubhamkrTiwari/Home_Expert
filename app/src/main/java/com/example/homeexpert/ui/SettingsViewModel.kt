package com.example.homeexpert.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Dummy data store for theme preference
object ThemeRepository {
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    fun setTheme(isDark: Boolean) {
        _isDarkTheme.value = isDark
    }
}

class SettingsViewModel : ViewModel() {

    val isDarkTheme: StateFlow<Boolean> = ThemeRepository.isDarkTheme

    fun enableDarkMode(enable: Boolean) {
        viewModelScope.launch {
            ThemeRepository.setTheme(enable)
        }
    }
}