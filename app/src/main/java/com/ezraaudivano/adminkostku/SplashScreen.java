package com.ezraaudivano.adminkostku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    public static final int mode = Activity.MODE_PRIVATE;
    private SharedPreferences preferences;
    String tempEmail= "";
    TextView helloTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadPreferences();
        if(!tempEmail.isEmpty())
        {
            helloTV = findViewById(R.id.textViewSplash);
            helloTV.setText(tempEmail);
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();

                    }
                }
            };
            thread.start();
        }else
        {
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        finish();

                    }
                }
            };
            thread.start();
        }

    }

    private void loadPreferences(){
        String name = "profile";
        preferences = getSharedPreferences(name, mode);
        if(preferences != null){
            tempEmail = preferences.getString("email", "");
        }
    }
}