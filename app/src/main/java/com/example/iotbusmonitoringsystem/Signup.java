package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {


    EditText emailEditText,userNameEditText,passwordEditText,mobileNumberEditText;
    Button signUpButton,loginIntentButton;
    Handler mainHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        emailEditText = findViewById(R.id.email_edit_text);
        userNameEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        mobileNumberEditText = findViewById(R.id.mobile_number_edit_text);

        signUpButton = findViewById(R.id.signup_button);
        loginIntentButton = findViewById(R.id.login_intent_button);

        loginIntentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();
                user.setEmail(emailEditText.getText().toString());
                user.setName(userNameEditText.getText().toString());
                user.setPhoneNo(mobileNumberEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                if(user.getEmail().equals("") || user.getName().equals("") || user.getPassword().equals("") || user.getPhoneNo().equals(""))
                {
                    Toast.makeText(Signup.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    register(user);
                }


            }
        });
    }
    private void register(final User user)
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://androstar.tk/bus_sit_management_system/api.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if(response.equals("email") || response.equals("user"))
                            {
                                Toast.makeText(getApplicationContext(),"Couldn't Sign Up. \nEmail Already Exists",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(Signup.this,"Your are registered successfully..",Toast.LENGTH_SHORT).show();
                                mainHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                    }
                                },500);
                            }
                        }catch (Exception e)
                        {
                            //Toast.makeText(getApplicationContext(), "Json Exception", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            /* passing request body */
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>() ;
                params.put("apiKey","1999");
                params.put("email",user.getEmail());
                params.put("username",user.getName());
                params.put("password",user.getPassword());
                params.put("phone_no",user.getPhoneNo());

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
