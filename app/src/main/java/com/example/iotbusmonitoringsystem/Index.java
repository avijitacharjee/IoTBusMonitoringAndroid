package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void status(View view) {
        startActivity(new Intent(getApplicationContext(),BusListActivity.class));
    }

    public void booking(View view) {
        startActivity(new Intent(getApplicationContext(),SeatBooking.class));
    }
}
