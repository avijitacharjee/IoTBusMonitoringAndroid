package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Start extends AppCompatActivity {
    Button statusIntentButton, locationIntentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        statusIntentButton = findViewById(R.id.status_intent);
        statusIntentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BusListActivity.class));
            }
        });
        findViewById(R.id.location_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }
    public void getLocation()
    {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://androstar.tk/bus_sit_management_system/api.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String loc="";
                try{
                    JSONObject resp = new JSONObject(response);
                    JSONArray respArr = resp.getJSONArray("data");
                    JSONObject sits = respArr.getJSONObject(0);
                    loc = sits.getString("location");
                }catch (Exception e)
                {
                    //loc="0.00,0.00";
                }
                Intent intent = new Intent(getApplicationContext(),BusLocationActivity.class);
                intent.putExtra("location",loc);
                startActivity(intent);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            /* passing request body */
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>() ;
                params.put("apiKey","1999");
                params.put("locationStatus","1");

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
