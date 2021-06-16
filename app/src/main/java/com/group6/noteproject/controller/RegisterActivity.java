package com.group6.noteproject.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group6.noteproject.R;
import com.group6.noteproject.model.Account;
import com.group6.noteproject.model.User;
import com.group6.noteproject.service.UserService;
import com.group6.noteproject.util.ValidationUtils;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;                            // user service
    Context context;                                    // register activity's context

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = this;                                 // register activity's context

        userService = new UserService(this);     // instantiate user service
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

        ValidationUtils validationUtils = new ValidationUtils(userService);     // instantiate validation utils

        /* Validate inputs and store validation results */
        int validateUsernameResult = validationUtils.validateUsername(regUsername);
        int validatePasswordResult = validationUtils.validatePassword(regPassword, regConfirmPassword);
        int validateFullNameResult = validationUtils.validateFullName(regFullName);
        int validateEmailResult = validationUtils.validateEmail(regEmail);
        int validatePhoneResult = validationUtils.validatePhone(regPhone);
        int validateBirthdateResult = validationUtils.validateBirthdate(regBirthdate);

        /* Set errors and mark inputs as invalid according to the validation results */

        if (validateUsernameResult == 1) {
            etUsername.setError("Username must not be empty!");
            isValidInput = false;
        } else if (validateUsernameResult == 2) {
            etUsername.setError("Username already exists!\n" +
                    "Please enter another username!");
            isValidInput = false;
        }

        if (validatePasswordResult == 1) {
            etPassword.setError("Password must not be empty!");
            isValidInput = false;
        } else if (validatePasswordResult == 2){
            etPassword.setError("Password must contain minimum of 8 characters: at least 1 uppercase letter, \n" +
                    "1 lowercase letter, 1 number and 1 special character");
            isValidInput = false;
        } else if (validatePasswordResult == 3) {
            etConfirmPassword.setError("Password confirmation must match password!");
            isValidInput = false;
        }

        if (validateFullNameResult == 1) {
            etFullName.setError("Full Name must not be empty!");
            isValidInput = false;
        }

        if (validateEmailResult == 1) {
            etEmail.setError("Email must be in correct format!\n" +
                    "Example: abc@gmail.com");
            isValidInput = false;
        }

        if (validatePhoneResult == 1) {
            etPhone.setError("Phone must be in correct format!\n" +
                    "Example: +84823456789 or 0823456789");
            isValidInput = false;
        }

        if (validateBirthdateResult == 1){
            etBirthdate.setError("Birth Date must be in dd/MM/yyyy format!");
            isValidInput = false;
        } else if (validateBirthdateResult == 2){
            etBirthdate.setError("Birth Date must be today or a day in the past!");
            isValidInput = false;
        }

        /* If all input fields are valid */
        if (isValidInput) {
            // create new account object and set account info
            Account account = new Account();
            account.setUsername(regUsername);
            account.setPassword(regPassword);

            // create new user object and set user info
            User user = new User();
            user.setFullName(regFullName);
            user.setEmail(regEmail);
            user.setAddress(regAddress);
            user.setPhone(regPhone);
            user.setBirthdate(regBirthdate);

            boolean registerResult = userService.register(account, user);   // register the user

            // if registered successfully, shows message and returns to login screen, else only show message
            if (registerResult) {
                Toast.makeText(context, "Registration Successful!",
                        Toast.LENGTH_SHORT).show();
                finish();                                                   // finish the activity
            } else {
                Toast.makeText(context, "Registration Failed!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}