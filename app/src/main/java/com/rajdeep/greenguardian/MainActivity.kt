package com.rajdeep.greenguardian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var login_email: EditText
    lateinit var login_password: EditText
    lateinit var signup_button: Button
    lateinit var txtGoToRed: TextView
    lateinit var sharedPreferences: SharedPreferences

    var validEmail = mutableListOf<String>("abc@xyz")
    var validPass = mutableListOf<String>("123456")
    var name = mutableListOf<String>("Rakesh")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_email = findViewById(R.id.login_email)
        login_password = findViewById(R.id.login_passsword)
        signup_button = findViewById(R.id.signup_button)
        txtGoToRed = findViewById(R.id.txtMain)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        validEmail.add(sharedPreferences.getString("newEmail", "My Preferences").toString())
        validPass.add(sharedPreferences.getString("newPassword", "My Preferences").toString())
        name.add(sharedPreferences.getString("name", "My Preferences").toString())

        var delete: Boolean = sharedPreferences.getBoolean("Delete", false)

        if (!delete) {

            if (isLoggedIn) {
                val intent = Intent(this@MainActivity, ManuBar::class.java)
                startActivity(intent)
                finish()
            }
        }

        if (delete) {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            sharedPreferences.edit().putBoolean("Delete", false).apply()
            sharedPreferences.edit().clear().apply()
        }

        txtGoToRed.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        signup_button.setOnClickListener {
            var email = login_email.text.toString()
            var pass = login_password.text.toString()

            if (validEmail.contains(email) && validPass.contains(pass)) {

                var i: Int = validEmail.indexOf(email)

                savePreferences(email, name[i], pass)
                var intent = Intent(this@MainActivity, ManuBar::class.java)
                startActivity(intent)
                finish()

            }

            else {
                Toast.makeText(
                    this@MainActivity,
                    "Wrong Email or Password!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    fun savePreferences(title: String, name: String, pass: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("Title", title).apply()
        sharedPreferences.edit().putString("Name", name).apply()
        sharedPreferences.edit().putString("Password", pass)
    }

    override fun onPause() {
        finish()
        super.onPause()
    }
}