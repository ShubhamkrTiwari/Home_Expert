package com.example.homeexpert.data.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeexpert.data.SupabaseClient
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

sealed interface AuthState {
    val value: Any

    object Idle : AuthState
    object Loading : AuthState
    object Success : AuthState
    data class Error(val message: String) : AuthState
}

data class OtpVerificationState(val isSuccess: Boolean = false, val isLoading: Boolean = false, val error: String? = null)

class AuthViewModel : ViewModel() {
    private val _authState = mutableStateOf<AuthState>(AuthState.Idle)
    val authState: State<AuthState> = _authState

    private val _otpVerificationState = mutableStateOf(OtpVerificationState())
    val otpVerificationState: State<OtpVerificationState> = _otpVerificationState

    fun signUpWithEmail(email: String, password: String, name: String, phoneNumber: String, userRole: String?) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                withContext(Dispatchers.IO) {
                    SupabaseClient.client.auth.signUpWith(Email) {
                        this.email = email
                        this.password = password
                        data = buildJsonObject {
                            put("full_name", name)
                            put("phone_number", phoneNumber)
                            userRole?.let { put("role", it) }
                        }
                    }
                }
                _authState.value = AuthState.Success
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun loginWithEmail(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                withContext(Dispatchers.IO) {
                    SupabaseClient.client.auth.signInWith(Email) {
                        this.email = email
                        this.password = password
                    }
                }
                _authState.value = AuthState.Success
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun verifyEmailOTP(email: String, token: String) {
        viewModelScope.launch {
            _otpVerificationState.value = OtpVerificationState(isLoading = true, isSuccess = false, error = null)
            try {
                withContext(Dispatchers.IO) {
                    SupabaseClient.client.auth.verifyEmailOtp(type = OtpType.Email.SIGNUP, email = email, token = token)
                }
                _otpVerificationState.value = OtpVerificationState(isSuccess = true)
            } catch (e: Exception) {
                _otpVerificationState.value = OtpVerificationState(error = e.message ?: "An unknown error occurred")
            }
        }
    }
}
