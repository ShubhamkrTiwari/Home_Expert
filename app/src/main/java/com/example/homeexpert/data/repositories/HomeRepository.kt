package com.example.homeexpert.data.repositories

import com.example.homeexpert.data.Booking
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest

object HomeRepository {

    suspend fun getServices(): List<Service> {
        return SupabaseClient.client.postgrest.from("services").select().decodeList<Service>()
    }

    suspend fun getProfessionals(): List<Professional> {
        return SupabaseClient.client.postgrest.from("professionals").select().decodeList<Professional>()
    }

    suspend fun getBookings(): List<Booking> {
        return SupabaseClient.client.postgrest.from("bookings").select().decodeList<Booking>()
    }

    suspend fun addBooking(booking: Booking) {
        SupabaseClient.client.postgrest.from("bookings").insert(booking)
    }
}
