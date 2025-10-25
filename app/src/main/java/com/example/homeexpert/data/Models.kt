package com.example.homeexpert.data

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val name: String,
    val address: String
)

data class Professional(
    val id: String,
    val name: String,
    val service: Service,
    val rating: Float,
    val location: LatLng,
    val isFeatured: Boolean = false
)

data class Service(
    val id: String,
    val name: String,
    @DrawableRes val icon: Int // Resource ID for the service icon
)

data class LatLng(
    val latitude: Double,
    val longitude: Double
)

data class Booking(
    val id: String,
    val professional: Professional,
    val service: Service,
    val date: String,
    val time: String,
    val status: String // e.g., "Confirmed", "Completed", "Cancelled"
)
