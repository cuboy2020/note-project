package com.group6.noteproject.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;
import com.group6.noteproject.service.UserService;
import com.group6.noteproject.util.Constraint;

public class LoginActivity extends AppCompatActivity {

    UserService userService;    // user service
    Context context;            // login activity's context

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // disable night mode in the application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        context = this;         // login activity's context

        userService = new UserService(this);    // instantiate user service

        // Login button and its OnClick action
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * Get Account from database
             * check account existed and switch intent
             * @param v view
             */
            @Override
            public void onClick(View v) {
                /* Values from text fields on screen */
                EditText txtUsername = findViewById(R.id.et_username_login);
                EditText txtPassword = findViewById(R.id.et_password_login);

                // login using service
                Account account = userService.logIn(txtUsername.getText().toString(),
                        txtPassword.getText().toString());

                // if successful, redirect to main activity; else, shows toast message
                if (account != null) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(Constraint.ACCOUNT_KEY, account);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "The Username or Password is Incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // switch to register intent
        findViewById(R.id.tv_create_account).setOnClickListener(v -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
