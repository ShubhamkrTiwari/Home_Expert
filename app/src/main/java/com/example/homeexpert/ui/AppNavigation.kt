package com.example.homeexpert.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable(
            "login_signup/{userRole}",
            arguments = listOf(navArgument("userRole") { type = NavType.StringType })
        ) { backStackEntry ->
            LoginSignupScreen(navController, backStackEntry.arguments?.getString("userRole"))
        }

        // Using query parameter for optional userRole
        composable(
            "signup?userRole={userRole}",
            arguments = listOf(navArgument("userRole") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            SignUpScreen(navController, backStackEntry.arguments?.getString("userRole"))
        }

        // Using query parameter for optional userRole
        composable(
            "otp_verification/{phoneNumber}?userRole={userRole}",
            arguments = listOf(
                navArgument("phoneNumber") { type = NavType.StringType },
                navArgument("userRole") { type = NavType.StringType; nullable = true }
            )
        ) { backStackEntry ->
            OTPVerificationScreen(
                navController = navController,
                phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: "",
                userRole = backStackEntry.arguments?.getString("userRole")
            )
        }

        // Using query parameter for optional userRole
        composable(
            "profile_setup?userRole={userRole}",
            arguments = listOf(navArgument("userRole") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            ProfileSetupScreen(navController, backStackEntry.arguments?.getString("userRole"))
        }
        composable("services") {
            ServicesScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }

        composable("Professionals") {
            HomeScreen(navController)
        }
        composable(
            "service_detail/{serviceId}",
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) { backStackEntry ->
            ServiceDetailScreen(
                navController = navController,
                serviceId = backStackEntry.arguments?.getString("serviceId")
            )
        }
        composable(
            "booking_screen/{professionalId}",
            arguments = listOf(navArgument("professionalId") { type = NavType.StringType })
        ) { backStackEntry ->
            BookingScreen(
                navController = navController,
                professionalId = backStackEntry.arguments?.getString("professionalId")
            )
        }
        composable("booking_success") {
            BookingSuccessScreen(navController)
        }
        composable("my_bookings") {
            MyBookingsScreen(navController)
        }
        composable("customer_profile") {
            CustomerProfileScreen(navController)
        }
        composable("customer_notifications") {
            CustomerNotificationsScreen(navController)
        }
        composable("agent_dashboard") {
            AgentDashboardScreen(navController)
        }
        composable("booking_requests") {
            BookingRequestsScreen(navController)
        }
        composable(
            "job_details/{bookingId}",
            arguments = listOf(navArgument("bookingId") { type = NavType.StringType })
        ) { backStackEntry ->
            JobDetailsScreen(
                navController = navController,
                bookingId = backStackEntry.arguments?.getString("bookingId")
            )
        }
        composable("earnings_summary") {
            EarningsSummaryScreen(navController)
        }
        composable("agent_profile") {
            AgentProfileScreen(navController)
        }
        composable("notifications") {
            NotificationsScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("address") {
            AddressScreen(navController)
        }
        composable("payment") {
            PaymentScreen(navController)
        }
        composable(
            "chat/{professionalName}",
            arguments = listOf(navArgument("professionalName") { type = NavType.StringType })
        ) { backStackEntry ->
            ChatScreen(
                navController = navController,
                chatPartnerName = backStackEntry.arguments?.getString("professionalName") ?: ""
            )
        }

    }
}

@Composable
fun ServicesScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}