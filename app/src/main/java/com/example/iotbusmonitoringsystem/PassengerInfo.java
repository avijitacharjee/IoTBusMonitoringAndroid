package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PassengerInfo extends AppCompatActivity {
    TextView name,address,phone,payment,time,seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_into);
        name=findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        payment = findViewById(R.id.payment);
        time = findViewById(R.id.time);
        seat = findViewById(R.id.seat);

        name.setText(getIntent().getExtras().getString("name"));
        address.setText(getIntent().getExtras().getString("address"));
        phone.setText(getIntent().getExtras().getString("phone"));
        payment.setText(getIntent().getExtras().getString("payment"));
        time.setText(getIntent().getExtras().getString("time"));
        seat.setText(getIntent().getExtras().getString("seat"));
    }
}
