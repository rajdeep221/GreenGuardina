package com.rajdeep.greenguardian

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import org.w3c.dom.Text

class OrderList : AppCompatActivity() {

    lateinit var orderListCard: CardView
    lateinit var noOrderYet: CardView
    lateinit var orderType: TextView
    lateinit var delevery: TextView
    lateinit var orderDetails: TextView
    lateinit var btnCancel: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        orderDetails = findViewById(R.id.orderDetali)
        delevery = findViewById(R.id.delevery)
        noOrderYet = findViewById(R.id.noOrderYet)
        orderType = findViewById(R.id.orderType)
        orderListCard = findViewById(R.id.orderListCard)
        btnCancel = findViewById(R.id.btnCancel)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        var s1: String = sharedPreferences.getString("Name", "My Preferences").toString()
        var s2: String = sharedPreferences.getString("Number", "My Preferences").toString()
        var s3: String = sharedPreferences.getString("Street", "My Preferences").toString()
        var s4: String = sharedPreferences.getString("Area", "My Preferences").toString()
        var s5: String = sharedPreferences.getString("City", "My Preferences").toString()
        var s6: String = sharedPreferences.getString("Pin", "My Preferences").toString()
        var s7: String = sharedPreferences.getString("Date", "My Preferences").toString()

        var detailList: String = "$s1 $s2 $s3 $s4 $s5 $s6"
        orderDetails.text = detailList
        delevery.text = delevery.text.toString() + s7

        var isOrdered = sharedPreferences.getBoolean("isOrdered", false)

        var update = sharedPreferences.getBoolean("ordered", false)

        if (update) {
            isOrdered = true
            order()
        }

        if(isOrdered) {

            orderListCard.visibility = View.VISIBLE
            noOrderYet.visibility = View.INVISIBLE
        } else {
            println("Third")
            orderListCard.visibility = View.INVISIBLE
            noOrderYet.visibility = View.VISIBLE
        }

        btnCancel.setOnClickListener {
            sharedPreferences.edit().putBoolean("isOrdered", false)
            sharedPreferences.edit().putBoolean("ordered", false).apply()
            orderListCard.visibility = View.INVISIBLE
            noOrderYet.visibility = View.VISIBLE
        }

    }

    override fun onPause() {
        super.onPause()
        val intent = Intent(this@OrderList, ManuBar::class.java)
        startActivity(intent)
        finish()
    }

    fun order() {
        sharedPreferences.edit().putBoolean("isOrdered", true);
    }
}