package com.example.firebaseauth.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseauth.data.APIObject
import com.example.firebaseauth.databinding.ActivitySendNotificationBinding
import com.example.firebaseauth.model.NotificationData
import com.example.firebaseauth.model.PushNotification
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendNotificationActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySendNotificationBinding
    private lateinit var notification: PushNotification
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySendNotificationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            viewBinding.edtToken.setText(it)
        }

        viewBinding.btnSend.setOnClickListener {
            PushNotification(
                    NotificationData(
                            viewBinding.edtTitle.text.toString(),
                            viewBinding.edtMessage.text.toString()
                    ), viewBinding.edtToken.text.toString()).also {
                sendNotification(it)
            }
        }
        FirebaseMessaging.getInstance().subscribeToTopic(Companion.TOPIC)

    }

    private fun sendNotification(it: PushNotification) {
        APIObject.getInstance().sendNotification(it).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d(TAG, "onResponse: ${response.body()?.string()}")
            }

        })
    }

    companion object {
        private const val TAG = "SendNotificationActivity"
        private const val TOPIC = "/topics/test"
    }
}