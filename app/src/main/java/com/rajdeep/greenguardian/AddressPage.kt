package com.rajdeep.greenguardian

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddressPage : AppCompatActivity() {

    lateinit var txtDate: TextView
    lateinit var edtName: EditText
    lateinit var edtNumber: EditText
    lateinit var edtHouseno: EditText
    lateinit var area: EditText
    lateinit var autoCity: AutoCompleteTextView
    lateinit var pin: EditText
    lateinit var btnSubmit: Button
    lateinit var sharedPreferences: SharedPreferences

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_page)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        edtName = findViewById(R.id.edtName)
        edtNumber = findViewById(R.id.edtNumber)
        edtHouseno = findViewById(R.id.edtHouseno)
        area = findViewById(R.id.edtArea)
        autoCity = findViewById(R.id.autoCompleteTextView)
        pin = findViewById(R.id.edtPIN)

        title = "ADDRESS"
        val languages = resources.getStringArray(R.array.city_name)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)

        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            var name: String = edtName.text.toString()
            var number: String = edtNumber.text.toString()
            var houseNo: String = edtHouseno.text.toString()
            var at : String = area.text.toString()
            var cityName: String = autoCity.text.toString()
            var pinNo: String = pin.text.toString()
            var date: String = txtDate.text.toString()

            if (name.length < 2) {
                edtName.error = "Invalid Input"
            }

            else if (number.length != 10) {
                edtNumber.error = "The mobile no is not valid"
            }

            else if (houseNo.isEmpty()) {
                edtHouseno.error = "Enter the House Number"
            }

            else if (at.isEmpty()) {
                area.error = "Enter the Area"
            }

            else if (cityName == "Choose City") {
                autoCity.error = "Choose a City"
            }

            else if (pinNo.isEmpty()) {
                pin.error = "Enter the PIN Code"
            }

            else if (date == "Pick Up Date") {
                txtDate.error = "Enter a Date"
            } else {

                savePreference(name, number, houseNo, at, cityName, pinNo, date)
                val intent = Intent(this@AddressPage, OrderList::class.java)
                startActivity(intent)

            }
        }

        txtDate = findViewById(R.id.txtDate)

        txtDate.setOnClickListener {
            showDataPicker()
        }

    }

    override fun onPause() {
        super.onPause()
        intent = Intent(this@AddressPage, ManuBar::class.java)
        startActivity(intent)
        finish()
    }

    private fun showDataPicker() {
        val datePickerDialog = DatePickerDialog(this,{DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate: String = dateFormat.format(selectedDate.time)
            txtDate.text = formattedDate
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun savePreference(
        name: String,
        no: String, street: String,
        area: String, city: String,
        pin: String, date: String) {
        sharedPreferences.edit().putBoolean("ordered", true).apply()
        sharedPreferences.edit().putString("Number", no).apply()
        sharedPreferences.edit().putString("Street", street).apply()
        sharedPreferences.edit().putString("Area", area).apply()
        sharedPreferences.edit().putString("Pin", pin).apply()
        sharedPreferences.edit().putString("Name", name).apply()
        sharedPreferences.edit().putString("City", city).apply()
        sharedPreferences.edit().putString("Date", date).apply()
    }

}