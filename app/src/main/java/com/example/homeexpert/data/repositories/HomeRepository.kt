package com.example.homeexpert.data.repositories

import com.example.homeexpert.R
import com.example.homeexpert.data.Booking
import com.example.homeexpert.data.Professional
import com.example.homeexpert.data.Service
import com.example.homeexpert.data.LatLng

class HomeRepository {

    fun getServices(): List<Service> {
        return listOf(
            Service("1", "Electrician", R.drawable.electricionicon),
            Service("2", "Plumber", R.drawable.plumberservice),
            Service("3", "Tutor", R.drawable.tutoservice),
            Service("4", "Carpenter", R.drawable.carpenter),
            Service("5", "Painter", R.drawable.paintericon),
            Service("6", "Cleaner", R.drawable.cleanericon)
        )
    }

    fun getProfessionals(): List<Professional> {
        return listOf(
            Professional("1", "Amit Kumar", getServices()[0], 4.5f, LatLng(12.9716, 77.5946)),
            Professional("2", "Ravi Kumar", getServices()[1], 4.8f, LatLng(12.9716, 77.5946), isFeatured = true),
            Professional("3", "Rakesh Kumar", getServices()[2], 4.2f, LatLng(12.9716, 77.5946))
        )
    }

    fun getBookings(): List<Booking> {
        return listOf(
            Booking("1", getProfessionals()[0], getServices()[0], "2024-08-15", "10:00 AM", "Confirmed"),
            Booking("2", getProfessionals()[1], getServices()[1], "2024-08-16", "02:00 PM", "Completed"),
            Booking("3", getProfessionals()[2], getServices()[2], "2024-08-18", "11:00 AM", "Confirmed")
        )
    }
}
