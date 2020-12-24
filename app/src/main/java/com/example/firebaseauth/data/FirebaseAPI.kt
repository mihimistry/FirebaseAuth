package com.example.firebaseauth.data

import com.example.firebaseauth.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseAPI {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com/"
        const val CONTENT_TYPE="application/json"
        const val SERVER_KEY = "AAAAjOsHi1Q:APA91bHSjheTeKQKhrpoJTiKWysoOb93Hbify1VQfYzKQTSXb9Cf5CPMfHH4abGUxB2eM8EiHTFm_aAsJjZm0mYY60fADLfwsAuyEow_WoMXnVKS3poNdD-Uu8rDWEKThHy8nGfaTkA-"
    }

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    fun sendNotification(
            @Body notification: PushNotification
    ): Call<ResponseBody>
}