package com.rajdeep.greenguardian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        title = "Notification"
    }

    override fun onPause() {
        super.onPause()
        var intent = Intent(this@Notification, ManuBar::class.java)
        startActivity(intent)
        finish()
    }
}