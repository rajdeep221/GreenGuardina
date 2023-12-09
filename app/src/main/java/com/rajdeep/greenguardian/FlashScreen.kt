package com.rajdeep.greenguardian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class FlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_screen)



        Handler().postDelayed({
            var intent = Intent(this@FlashScreen, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}