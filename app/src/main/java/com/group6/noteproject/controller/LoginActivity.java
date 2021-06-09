package com.group6.noteproject.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;
import com.group6.noteproject.service.UserService;

public class LoginActivity extends AppCompatActivity {

    UserService userService;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        userService = new UserService(this);
        Account account = new Account();
        account.setUsername("thang");
        account.setPassword("123");
        User user = new User();
        userService.register(account, user);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtUsername = findViewById(R.id.et_username_login);
                EditText txtPassword = findViewById(R.id.et_password_login);
                Account account = userService.logIn(txtUsername.getText().toString(),
                        txtPassword.getText().toString());

                if(account != null){
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(context, "The Username or Password is Incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.tv_create_account).setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    public void onClick(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
