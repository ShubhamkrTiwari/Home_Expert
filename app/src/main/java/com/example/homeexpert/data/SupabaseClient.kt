package com.example.homeexpert.data

import android.graphics.Bitmap
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.plugins.SupabasePluginProvider
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

private val com.example.homeexpert.data.SupabaseClient.GoTrue: Any
    get() {
        TODO()
    }

object SupabaseClient {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://upjzswprcdeqgtijlxfn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVwanpzd3ByY2RlcWd0aWpseGZuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjE1ODAxMjgsImV4cCI6MjA3NzE1NjEyOH0.FhlDgfRoh6L2OIg4JQWZRSAMbbrtPuW-IPT5I9ZSbq4"
    ) {
        install(Postgrest)
        install(Storage)
    }

    fun install(plugin: Any) {
        TODO("Not yet implemented")
    }
}
