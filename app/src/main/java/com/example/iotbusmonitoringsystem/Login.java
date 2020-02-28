package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

    private EditText userNameEditText,passwordEditText;
    private Button loginButton,signUpButton;
    // Handler mainHandler = new Handler();
    public boolean isRegistered=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        userNameEditText = findViewById(R.id.user_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.signup_intent_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(userNameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                isRegistered(user);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup.class));
            }
        });






    }

    private void isRegistered(final User user)
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://androstar.tk/bus_sit_management_system/api.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        try {

                            if(response.equals("user"))
                            {
                                Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Start.class));
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Incorrect mail/password", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e)
                        {
                            // Toast.makeText(LoginActivity.this, "Json Exception", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*textView.setText("Failed"+error.toString());*/
                //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){

            /* passing request body */
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>() ;
                params.put("apiKey","1999");
                params.put("email",user.getEmail());
                params.put("username","");
                params.put("password",user.getPassword());
                params.put("phone_no","");

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

        // Add the request to the RequestQueue.
        //getApplicationContext().addToRequestQueue(jsonObjectRequest, "headerRequest");
        queue.add(stringRequest);

    }
}
