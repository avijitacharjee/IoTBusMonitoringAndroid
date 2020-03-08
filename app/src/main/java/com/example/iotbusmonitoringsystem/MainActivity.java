package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

import static com.example.iotbusmonitoringsystem.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    Handler mainHandler;
    TextView seat1TextView, seat2TextView,seat3TextView;
    Button notificationButton;
    private NotificationManagerCompat notificationManager;
    String notificationTitle="";
    String notificationMessage = "";
    boolean notiFlag=false;
    Switch switch1, switch2, switch3;
    Button mapIntentButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new Handler();

        seat1TextView = findViewById(R.id.sit1);
        seat2TextView = findViewById(R.id.sit2);
        seat3TextView = findViewById(R.id.sit3);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        //mapIntentButton = findViewById(R.id.go_to_map_intent_button);
        switch1.setVisibility(View.GONE);
        switch2.setVisibility(View.GONE);
        switch3.setVisibility(View.GONE);


        notificationManager = NotificationManagerCompat.from(this);
        checkVol();
        volley();
        mainHandler.postDelayed(runnable,300);


    }
    public void checkVol()
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://androstar.tk/bus_sit_management_system/api.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resp = new JSONObject(response);
                            JSONArray respArr = resp.getJSONArray("data");
                            JSONObject sits = respArr.getJSONObject(0);
                            String sit1 = sits.getString("sit1");
                            String sit2 = sits.getString("sit2");
                            String sit3 = sits.getString("sit3");

                            if(sit1.equalsIgnoreCase("1"))
                            {
                                switch1.setChecked(true);
                            }
                            else
                            {
                                switch1.setChecked(false);
                            }
                            if(sit2.equalsIgnoreCase("1"))
                            {
                                switch2.setChecked(true);
                            }
                            else
                            {
                                switch2.setChecked(false);
                            }
                            if(sit3.equalsIgnoreCase("1"))
                            {
                                switch3.setChecked(true);
                            }
                            else
                            {
                                switch3.setChecked(false);
                            }

                        } catch (Exception e)

                        {

                        }

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
                params.put("apiKey","1999");
                params.put("bookStatus","1");

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

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            volley();

            mainHandler.postDelayed(runnable,1000);
        }
    };

    public void volley()
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://androstar.tk/bus_sit_management_system/api.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject resp = new JSONObject(response);
                            JSONArray respArr = resp.getJSONArray("data");
                            JSONObject sits = respArr.getJSONObject(0);
                            String sit1 = sits.getString("sit1");
                            String sit2 = sits.getString("sit2");
                            String sit3 = sits.getString("sit3");

                            float s1 = Float.parseFloat(sit1);
                            float s2 = Float.parseFloat(sit2);
                            float s3 = Float.parseFloat(sit3);
                            if(!switch1.isChecked())
                            {
                                if (s1 > 0)
                                {
                                    if(!seat1TextView.getText().equals("Seat 1 is occupied"))
                                    {
                                        seat1TextView.setText("Seat 1 is occupied");
                                        sendOnChannel1("Seat status", "Seat 1 is occupied");
                                        seat1TextView.setTextColor(Color.RED);
                                    }
                                }
                                else
                                {
                                    seat1TextView.setText("Seat 1 is empty");
                                    seat1TextView.setTextColor(Color.BLACK);
                                }
                            }
                            if(!switch2.isChecked())
                            {
                                if(s2>0)
                                {
                                    if (!seat2TextView.getText().equals("Seat 2 is occupied"))
                                    {
                                        seat2TextView.setText("Seat 2 is occupied");
                                        seat2TextView.setTextColor(Color.RED);
                                        sendOnChannel1("Seat status", "Seat 2 is occupied");
                                    }
                                }
                                else
                                {
                                    seat2TextView.setTextColor(Color.BLACK);
                                    seat2TextView.setText("Seat 2 is empty");
                                }
                            }
                            if(!switch3.isChecked())
                            {
                                if(s3>0)
                                {
                                    if (!seat3TextView.getText().equals("Seat 3 is occupied")) {
                                        seat3TextView.setText("Seat 3 is occupied");
                                        seat3TextView.setTextColor(Color.RED);
                                        sendOnChannel1("Seat status", "Seat 3 is occupied");
                                    }
                                }
                                else
                                {
                                    seat3TextView.setTextColor(Color.BLACK);
                                    seat3TextView.setText("Seat 3 is empty");
                                }
                            }

                        }catch (Exception e)
                        {

                        }
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
                params.put("apiKey","1999");
                params.put("sitStatus","1");

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

    public void sendOnChannel1(String title,String message) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}
