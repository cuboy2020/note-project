package com.group6.noteproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;
import com.group6.noteproject.service.UserService;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        userService = new UserService(this);

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Get EditText Views */
                EditText etUsername = (EditText) findViewById(R.id.et_username_reg);
                EditText etPassword = findViewById(R.id.et_password_reg);
                EditText etConfirmPassword = findViewById(R.id.et_confirm_password_reg);
                EditText etFullName = findViewById(R.id.et_fullname_reg);
                EditText etEmail = findViewById(R.id.et_email_reg);
                EditText etAddress = findViewById(R.id.et_address_reg);
                EditText etPhone = findViewById(R.id.et_phone_reg);
                EditText etBirthdate = findViewById(R.id.et_birthdate_reg);

                /* Create local variables to store the EditText Views' current values */
                String regUsername = etUsername.getText().toString();
                String regPassword = etPassword.getText().toString();
                String regConfirmPassword = etConfirmPassword.getText().toString();
                String regFullName = etFullName.getText().toString();
                String regEmail = etEmail.getText().toString();
                String regAddress = etAddress.getText().toString();
                String regPhone = etPhone.getText().toString();
                String regBirthdate = etBirthdate.getText().toString();

                /* Input validation */
                boolean isValidInput = true;        // flag to check if all input fields are valid

                if (TextUtils.isEmpty(regUsername)) {
                    etUsername.setError("Username must not be empty!");
                    isValidInput = false;
                } else {
                    if (userService.getUserAccount(regUsername) != null){
                        etUsername.setError("Username already exists!\n" +
                                "Please enter another username!");
                        isValidInput = false;
                    }
                }

                if (TextUtils.isEmpty(regPassword)) {
                    etPassword.setError("Password must not be empty!");
                    isValidInput = false;
                } else {
                    if (!regPassword.equals(regConfirmPassword)){
                        etConfirmPassword.setError("Password confirmation must match password!");
                        isValidInput = false;
                    }
                }

                if (TextUtils.isEmpty(regFullName)) {
                    etFullName.setError("Full Name must not be empty!");
                    isValidInput = false;
                }

                if (isValidInput){
                    Account account = new Account();
                    account.setUsername(regUsername);
                    account.setPassword(regPassword);

                    User user = new User();
                    user.setFullname(regFullName);
                    user.setEmail(regEmail);
                    user.setAddress(regAddress);
                    user.setPhone(regPhone);
                    user.setBirthdate(regBirthdate);

                    boolean registerResult = userService.register(account, user);

                    if (registerResult){
                        Toast.makeText(context, "Registration Successful!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Registration Failed!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}