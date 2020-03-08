package com.example.iotbusmonitoringsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PassengerInto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passenger_into)
        var name= findViewById<TextView>(R.id.name)
    }
}
