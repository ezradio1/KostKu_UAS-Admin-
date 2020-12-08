package com.ezraaudivano.adminkostku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    Button btnLogin;
    private SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;
    String tempEmail = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            loadPreferences();
            if(!tempEmail.isEmpty())
            {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            txtEmail = findViewById(R.id.emailLog);
            txtPassword = findViewById(R.id.passwordLog);
            btnLogin = findViewById(R.id.btnlogin);


            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    System.out.println("email "+ txtEmail.getText());
                    System.out.println("pass "+ txtPassword.getText());
                    if(txtEmail.getText().toString().equalsIgnoreCase("admin@kostku.com") && txtPassword.getText().toString().equalsIgnoreCase("admin"))
                    {
                        savedPreferences();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    }else if (!txtEmail.getText().toString().equalsIgnoreCase("admin@kostku.com") && !txtPassword.getText().toString().equalsIgnoreCase("admin"))
                    {
                        txtEmail.setError("Invalid Email");
                        txtPassword.setError("Invalid Password");
                    }
                    else if(!txtEmail.getText().toString().equalsIgnoreCase("admin@kostku.com")){
                        txtEmail.setError("Invalid Email");
                    }
                    else if(!txtPassword.getText().toString().equalsIgnoreCase("admin")){
                        txtPassword.setError("Invalid Password");
                    }
                }
            });
        }

    public void savedPreferences () {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", txtEmail.getText().toString());
        editor.apply();
    }

    private void loadPreferences(){
        String name = "profile";
        preferences = getSharedPreferences(name, mode);
        if(preferences != null){
            tempEmail = preferences.getString("email", "");
        }
    }
}