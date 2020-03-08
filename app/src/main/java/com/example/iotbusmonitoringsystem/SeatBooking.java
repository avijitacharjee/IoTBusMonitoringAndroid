package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class SeatBooking extends AppCompatActivity {
    static Spinner spinner;
    Button bookButton;
    EditText nameEditText,phoneEditText,addressEditText,paymentEditText,timeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_booking);

        spinner = findViewById(R.id.seat_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(Color.WHITE);

        nameEditText = findViewById(R.id.name_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        paymentEditText = findViewById(R.id.payment_edit_text);
        timeEditText = findViewById(R.id.time_edit_text);

        bookButton = findViewById(R.id.book_button);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });
    }

    public void submit(View view) {
        Toast.makeText(this, ""+spinner.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
        volley(spinner.getSelectedItemPosition());
    }
    public void volley(int i)
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://androstar.tk/bus_sit_management_system/api.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(getApplicationContext(),PassengerInfo.class);
                        intent.putExtra("name",nameEditText.getText().toString());
                        intent.putExtra("phone",phoneEditText.getText().toString());
                        intent.putExtra("address",addressEditText.getText().toString());
                        intent.putExtra("time",timeEditText.getText().toString());
                        intent.putExtra("seat",spinner.getSelectedItem().toString());
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){

            /* passing request body */
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>() ;
                params.put("seatBooking","1");
                if(spinner.getSelectedItemPosition()==0)
                {
                    params.put("sit1","1");
                    params.put("sit2","0");
                    params.put("sit3","0");
                }
                if(spinner.getSelectedItemPosition()==1)
                {
                    params.put("sit1","0");
                    params.put("sit2","1");
                    params.put("sit3","0");
                }
                if(spinner.getSelectedItemPosition()==2)
                {
                    params.put("sit1","0");
                    params.put("sit2","0");
                    params.put("sit3","1");
                }

                return params;
            }
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };

        queue.add(stringRequest);
    }


}
