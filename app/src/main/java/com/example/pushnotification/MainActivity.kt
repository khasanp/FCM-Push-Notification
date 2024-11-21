package com.example.pushnotification

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pushnotification.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(/* view = */ binding.root)
        binding.getToken.setOnClickListener{
            FirebaseMessaging.getInstance().token.addOnCompleteListener{task ->
                if (!task.isSuccessful){
                    Log.d(TAG, "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }
                val token = task.result

                binding.token.setText(token)
               val toast = Toast.makeText(this@MainActivity, "get a token", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

}