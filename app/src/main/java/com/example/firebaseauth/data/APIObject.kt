package com.example.firebaseauth.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIObject {
    companion object {
        var firebaseAPI: FirebaseAPI? = null

        fun getInstance():FirebaseAPI{
            val retrofit=Retrofit.Builder()
                    .baseUrl(FirebaseAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            firebaseAPI=retrofit.create(FirebaseAPI::class.java)
            return firebaseAPI as FirebaseAPI
        }
    }
}