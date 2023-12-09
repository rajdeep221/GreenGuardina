package com.rajdeep.greenguardian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    lateinit var signup_email: EditText
    lateinit var signup_password: EditText
    lateinit var signup_comfirm: EditText
    lateinit var signup_button: Button
    lateinit var txtName: EditText
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        signup_password = findViewById(R.id.signup_passsword)
        signup_comfirm = findViewById(R.id.signup_confirm)
        signup_email = findViewById(R.id.signup_email)
        signup_button = findViewById(R.id.signup_button)
        txtName = findViewById(R.id.txtName)

        signup_button.setOnClickListener {
            var email = signup_email.text.toString()
            var pass = signup_password.text.toString()
            var comPass = signup_comfirm.text.toString()
            var name = txtName.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Name content can't be filed",
                    Toast.LENGTH_LONG
                ).show()
            } else if (email.length < 4 || !email.contains('@')) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Invalid Email Id!!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (pass.length < 4) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Passwor Atleat Contain 5 Character!!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (pass != comPass) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Incorrect Password!!",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                savePreferences(email, pass, name)
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun savePreferences(email: String, password: String, name: String) {
        sharedPreferences.edit().putString("name", name).apply()
        sharedPreferences.edit().putString("newEmail", email).apply()
        sharedPreferences.edit().putString("newPassword", password).apply()
    }
}