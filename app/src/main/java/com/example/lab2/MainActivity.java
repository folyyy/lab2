package com.example.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    ArrayList name = new ArrayList();
    ArrayList graphic = new ArrayList();
    ArrayList helptext = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        Thread s = new Thread() {
            public void run() {
                try {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("name",name);
                    i.putExtra("graphic",graphic);
                    i.putExtra("helptext",helptext);
                    startActivity(i);
                    finish();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        parseData(s);
    }

    public void parseData(final Thread s) {
        String url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 1; i <= response.length()-1; i++) {
                        JSONObject obj = response.getJSONObject(i);
                        name.add(obj.getString("name"));
                        graphic.add(obj.getString("graphic"));
                        if (obj.has("helptext"))
                            helptext.add(obj.getString("helptext"));
                        else helptext.add(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                s.start();
            }
            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }); queue.add(request);
    }



}
