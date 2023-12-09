package com.rajdeep.greenguardian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class ManuBar : AppCompatActivity() {

    lateinit var ggDesc: TextView
    lateinit var newOrderCard: CardView
    lateinit var ordeListCard: CardView
    lateinit var profileCard: CardView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var notificationCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manu_bar)

        title = "HOME"
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        ordeListCard = findViewById(R.id.orderListCard)
        ggDesc = findViewById(R.id.ggDesc)
        newOrderCard = findViewById(R.id.newOrderCard)
        profileCard = findViewById(R.id.profileCard)
        notificationCard = findViewById(R.id.notificationCard)

        var email: String = sharedPreferences.getString("Title", "My Preferences").toString()
        var name: String = sharedPreferences.getString("Name", "My Preferences").toString()
        var pass: String = sharedPreferences.getString("Password", "My Preferences").toString()

        var list = email.split("@")
        ggDesc.text = list[0]

        ordeListCard.setOnClickListener(object: View.OnClickListener {

            override fun onClick(view: View?) {
                var intent = Intent(this@ManuBar, OrderList::class.java)
                startActivity(intent)
            }

        })

        newOrderCard.setOnClickListener(object: View.OnClickListener {

            override fun onClick(view: View?) {
                var intent = Intent(this@ManuBar, AddressPage::class.java)
                startActivity(intent)
            }

        })

        profileCard.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                var intent = Intent(this@ManuBar, Profile::class.java)
                savedPreference(name, email, list[0], pass)
                startActivity(intent)
            }
        })

        notificationCard.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                var intent = Intent(this@ManuBar, Notification::class.java)
                startActivity(intent)
            }

        })

        var temp: Boolean = sharedPreferences.getBoolean("arg", false);
        println(temp)

    }

    fun savedPreference(name: String, email: String, id: String, pass: String) {
        sharedPreferences.edit().putBoolean("arg", false).apply()
        sharedPreferences.edit().putString("Name", name).apply()
        sharedPreferences.edit().putString("Email", email).apply()
        sharedPreferences.edit().putString("Id", id).apply()
        sharedPreferences.edit().putString("Password", pass).apply()
    }

    override fun onPause() {
        finish()
        super.onPause()
    }

}