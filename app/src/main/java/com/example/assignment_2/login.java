package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import common.LocalSession;

public class login extends AppCompatActivity {

    TextInputLayout emailLayout, passLayout;
    TextInputEditText emailInput, passInput;
    Button btnLogin;
    LocalSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.email_input);
        emailLayout = findViewById(R.id.email_layout);
        passInput = findViewById(R.id.password_input);
        passLayout = findViewById(R.id.password_layout);
        btnLogin = findViewById(R.id.btn_login);

        session = new LocalSession(login.this);

        btnLogin.setOnClickListener((v->{
            emailLayout.setError(null);
            passLayout.setError(null);
            String email = String.valueOf(emailInput.getText()).trim();
            String pass = String.valueOf(passInput.getText()).trim();

            if(validateFields(email,pass)){
                //login procedure
                callSnackBar("Login successful");
                session.setLoginStatus();
            }
        }));
    }

    public void callSnackBar(String msg){
        Snackbar.make(login.this,findViewById(R.id.login_layout),msg,Snackbar.LENGTH_SHORT).show();
    }

    private boolean validateFields(String email, String pass) {
        if(email.isEmpty())
            emailLayout.setError("Please provide your email");
        else if(pass.isEmpty())
            passLayout.setError("Please enter your password");
        else if(pass.length() < 6)
            passLayout.setError(("Password must be at least of 6 character"));
        else
            return true;
        return false;

    }

    public void navToSignUp(View view) {
        new Handler().postDelayed(()->{
            Intent navIntent = new Intent(login.this, registration.class);
            startActivity(navIntent);
        }, 150);
    }
}