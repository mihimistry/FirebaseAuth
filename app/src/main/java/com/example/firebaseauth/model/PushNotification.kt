package com.example.firebaseauth.model

data class PushNotification(
        val data: NotificationData,
        val to: String
)