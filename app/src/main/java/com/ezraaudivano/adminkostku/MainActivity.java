package com.ezraaudivano.adminkostku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ezraaudivano.adminkostku.API.KostAPI;
import com.ezraaudivano.adminkostku.API.UserAPI;
import com.ezraaudivano.adminkostku.Adapters.AdapterUser;
import com.ezraaudivano.adminkostku.Model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterUser adapter;
    private List<User> listUser;
    private Button floatingActionButton, btnLogout;
    SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.refreshBtn);
        btnLogout = findViewById(R.id.logoutBtn);
        System.out.println("TEST");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser();

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedPreferences();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        setAdapter();
        getUser();
    }

    public void setAdapter() {
        this.setTitle("Data Pemilik Kost");
        listUser = new ArrayList<User>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new AdapterUser(this, listUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void getUser() {
        RequestQueue queue = Volley.newRequestQueue(this);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        ;
        progressDialog.setTitle("Menampilkan data user");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, UserAPI.URL_SELECT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = response.getJSONArray("data");


                    if (!listUser.isEmpty())
                        listUser.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        String fullname = jsonObject.optString("fullname");
                        String email = jsonObject.optString("email");
                        String jumlahKost = jsonObject.optString("jumlahKost");

                        User user = new User(fullname, email, Integer.parseInt(jumlahKost));

                        listUser.add(user);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, response.optString("message"), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat gangguan/error
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Disini proses penambahan request yang sudah kita buat ke request queue yang sudah dideklarasi
        queue.add(stringRequest);
    }

    public void savedPreferences() {
        String name = "profile";
        preferences = this.getSharedPreferences(name, mode);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", "");
        editor.apply();
    }


}