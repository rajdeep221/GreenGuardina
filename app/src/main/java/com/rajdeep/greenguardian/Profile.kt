package com.rajdeep.greenguardian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import kotlin.system.exitProcess

class Profile : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var txtNameBox: TextView
    lateinit var txtEmailBox: TextView
    lateinit var btnDelete: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        title = "My Profile"

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        textView = findViewById(R.id.textView)
        txtNameBox = findViewById(R.id.txtNameBox)
        txtEmailBox = findViewById(R.id.txtEmailBox)
        btnDelete = findViewById(R.id.btnDelete)

        sharedPreferences.edit().putBoolean("arg", true).apply()

        var id = sharedPreferences.getString("Id", "My Preferences").toString()
        var name = sharedPreferences.getString("Name", "My Preference").toString()
        var email = sharedPreferences.getString("Email", "My Preference").toString()
        var pass = sharedPreferences.getString("Password", "My Preferences").toString()

        btnDelete.setOnClickListener {

            sharedPreferences.edit().putBoolean("arg", false).apply();

            Toast.makeText(
                this@Profile,
                "Logging Out",
                Toast.LENGTH_LONG
            ).show()

            var intent = Intent(this@Profile, MainActivity::class.java)
            savedPreference()
            startActivity(intent)

        }

        textView.text = id
        txtNameBox.text = name
        txtEmailBox.text = email

    }

    override fun onPause() {
        super.onPause()

        var check = sharedPreferences.getBoolean("arg", false);

        if (check) {
            var intent = Intent(this@Profile, ManuBar::class.java)
            startActivity(intent)
        }
        finish()
    }

    fun savedPreference() {
        sharedPreferences.edit().putBoolean("Delete", true).apply();
    }
    
}