package com.group6.noteproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;
import com.group6.noteproject.service.UserService;
import com.group6.noteproject.util.Validation;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;

        userService = new UserService(this);
    }

    public void onClick(View v) {
        /* Get EditText Views */
        EditText etUsername = findViewById(R.id.et_username_reg);
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

        Validation validation = new Validation(userService);

        if (validation.validateUsername(regUsername) == 1) {
            etUsername.setError("Username must not be empty!");
            isValidInput = false;
        } else if (validation.validateUsername(regUsername) == 1) {
            etUsername.setError("Username already exists!\n" +
                    "Please enter another username!");
            isValidInput = false;
        }

        if (validation.validatePassword(regPassword, regConfirmPassword) == 1) {
            etPassword.setError("Password must not be empty!");
            isValidInput = false;
        } else if (validation.validatePassword(regPassword, regConfirmPassword) == 2){
            etPassword.setError("Password must contain minimum of 8 characters: at least 1 uppercase letter, \n" +
                    "1 lowercase letter, 1 number and 1 special character");
            isValidInput = false;
        } else if (validation.validatePassword(regPassword, regConfirmPassword) == 3) {
            etConfirmPassword.setError("Password confirmation must match password!");
            isValidInput = false;
        }

        if (validation.validateFullName(regFullName) == 1) {
            etFullName.setError("Full Name must not be empty!");
            isValidInput = false;
        }

        if (validation.validateEmail(regEmail) == 1) {
            etEmail.setError("Email must be in correct format!\n" +
                    "Example: abc@gmail.com");
            isValidInput = false;
        }

        if (validation.validatePhone(regPhone) == 1) {
            etPhone.setError("Phone must be in correct format!\n" +
                    "Example: +84834567890 or 0834567890");
            isValidInput = false;
        }

        if (isValidInput) {
            Account account = new Account();
            account.setUsername(regUsername);
            account.setPassword(regPassword);

            User user = new User();
            user.setFullName(regFullName);
            user.setEmail(regEmail);
            user.setAddress(regAddress);
            user.setPhone(regPhone);
            user.setBirthdate(regBirthdate);

            boolean registerResult = userService.register(account, user);

            if (registerResult) {
                Toast.makeText(context, "Registration Successful!",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(context, "Registration Failed!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}