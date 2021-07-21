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

public class registration extends AppCompatActivity {

    TextInputLayout nameLayout, emailLayout, passLayout, confirmPassLayout;
    TextInputEditText nameInput, emailInput, passInput, confirmPassInput;
    Button btnRegister;
    LocalSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameInput = findViewById(R.id.name_input);
        nameLayout = findViewById(R.id.name_layout);
        emailInput = findViewById(R.id.email_input);
        emailLayout = findViewById(R.id.email_layout);
        passInput = findViewById(R.id.password_input);
        passLayout = findViewById(R.id.password_layout);
        confirmPassInput = findViewById(R.id.confirm_password_input);
        confirmPassLayout = findViewById(R.id.confirm_password_layout);
        btnRegister = findViewById(R.id.btn_register);

        session = new LocalSession(registration.this);

        btnRegister.setOnClickListener((v->{
            nameLayout.setError(null);
            emailLayout.setError(null);
            passLayout.setError(null);
            confirmPassLayout.setError(null);
            String name = String.valueOf(nameInput.getText()).trim();
            String email = String.valueOf(emailInput.getText()).trim();
            String pass = String.valueOf(passInput.getText()).trim();
            String confirmPass = String.valueOf(confirmPassInput.getText()).trim();

            if(validateFields(name,email,pass,confirmPass)){
                //login procedure
                callSnackBar("Registration successful");
                session.setRegistrationStatus();
            }
        }));
    }

    public boolean validateFields(String name, String email, String pass, String confirmPass){
        if(name.isEmpty())
            nameLayout.setError("Please provide your name");
        else if(email.isEmpty())
            emailLayout.setError("please provide your email");
        else if(pass.isEmpty())
            passLayout.setError("please provide your password");
        else if(pass.length() < 6)
            passLayout.setError("password must be at least of 6 characters");
        else if(confirmPass.isEmpty())
            confirmPassLayout.setError("please confirm your password");
        else if(!pass.equals(confirmPass))
            confirmPassLayout.setError("confirm password not matched");
        else return true;
        return false;
    }

    public void callSnackBar(String msg){
        Snackbar.make(registration.this,findViewById(R.id.registration_layout),msg,Snackbar.LENGTH_SHORT).show();
    }

    public void navToLogin(View view) {
        new Handler().postDelayed(()->{
            Intent navIntent = new Intent(registration.this,login.class);
            startActivity(navIntent);
        },150);
    }
}