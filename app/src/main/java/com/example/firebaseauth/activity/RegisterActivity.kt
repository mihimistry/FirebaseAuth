package com.example.firebaseauth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityRegisterBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnRegister.setOnClickListener {

            /*
            val credential = EmailAuthProvider.getCredential(viewBinding.edtEmail.text.toString(), viewBinding.edtPass.text.toString())

            FirebaseAuth.getInstance().currentUser?.linkWithCredential(credential)?.addOnCompleteListener {
                if (it.isSuccessful) Toast.makeText(this, "Linked Successfully", Toast.LENGTH_SHORT).show()
                else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    Log.e(Companion.TAG, "onCreate: ", it.exception)
                }
            }
             */

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(viewBinding.edtEmail.text.toString(), viewBinding.edtPass.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                        else Toast.makeText(this, "" + it.exception, Toast.LENGTH_SHORT).show()
                    }
        }

        viewBinding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}