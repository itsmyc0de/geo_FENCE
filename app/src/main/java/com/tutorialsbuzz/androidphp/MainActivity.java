package com.tutorialsbuzz.androidphp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "http://api.tutorialsbuzz.com/Orders/take_order.php";
    String item_name;
    EditText item_et;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        item_et = (EditText) findViewById(R.id.item_et_id);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);
    }


    public void insert(View v) {

        PD.show();
        item_name = item_et.getText().toString();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        PD.dismiss();
                        item_et.setText("");
                        Toast.makeText(getApplicationContext(),
                                "Data Inserted Successfully",
                                Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
                Toast.makeText(getApplicationContext(),
                        "failed to insert", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("item_name", item_name);
                return params;
            }
        };

        // Adding request to request queue
        MyApplication.getInstance().addToReqQueue(postRequest);
    }

    public void read(View v) {
         Intent read_intent = new Intent(MainActivity.this, ReadData.class);
        startActivity(read_intent);
    }

}
