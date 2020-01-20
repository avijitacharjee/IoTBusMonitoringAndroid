package com.example.iotbusmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BusListActivity extends AppCompatActivity {

    private BusListAdapter busListAdapter;
    String[] busNames={"Bus 1", "Bus 2", "Bus 3","Bus 4","Bus 5","Bus 6","Bus 7","Bus 8","Bus 9","Bus 10"};
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        listView = findViewById(R.id.bus_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        });
        busListAdapter = new BusListAdapter(getApplicationContext(),busNames);
        listView.setAdapter(busListAdapter);
    }
}
