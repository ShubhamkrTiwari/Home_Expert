package com.example.homeexpert.data

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val address: String
)

@Serializable
data class Professional(
    val id: String,
    val name: String,
    val serviceId: String,
    val rating: Float,
    val location: LatLng,
    val isFeatured: Boolean = false
)

@Serializable
data class Service(
    val id: String,
    val name: String
)

@Serializable
data class LatLng(
    val latitude: Double,
    val longitude: Double
)

@Serializable
data class Booking(
    val id: String,
    val professionalId: String,
    val serviceId: String,
    val date: String,
    val time: String,
    val status: String // e.g., "Confirmed", "Completed", "Cancelled"
)

@Serializable
data class PaymentMethod(
    val id: String,
    val cardType: String, // e.g., "Visa", "Mastercard"
    val lastFourDigits: String,
    val expiryDate: String,
    @DrawableRes val cardIcon: Int
)

@Serializable
data class Address(
    val id: String,
    val label: String, // e.g., "Home", "Work"
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String
)
