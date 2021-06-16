package com.group6.noteproject.util;

import android.text.TextUtils;

import com.group6.noteproject.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/* Utils to validate inputs */
public class ValidationUtils {
    private final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])" +
            "(?=.*\\d)(?=.*[$&+,:;=?@#|'<>.^*()%!-])[A-Za-z\\d$&+,:;=?@#|'<>.^*()%!-]{8,}$";
    private final String emailRegex = "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
            "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
            "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
    private final String phoneRegex = "^(0[1-9][0-9]{8})|(\\+84[1-9][0-9]{8})$";
    private final SimpleDateFormat dateFormatter;   // date formatter
    private UserService userService;                // user service

    public ValidationUtils(UserService userService){
        this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatter.setLenient(false);
        this.userService = userService;
    }

    public int validateUsername(String username){
        if (TextUtils.isEmpty(username)) {
            return 1;
        } else if (userService.getAccountByUsername(username) != null) {
            return 2;
        }

        return 0;
    }

    public int validatePassword(String password, String confirmPassword){

        if (TextUtils.isEmpty(password)) {
            return 1;
        } else if (!password.matches(passwordRegex)) {
            return 2;
        } else if (!password.equals(confirmPassword)) {
            return 3;
        }

        return 0;
    }

    public int validateFullName(String fullName){
        if (TextUtils.isEmpty(fullName)) {
            return 1;
        }

        return 0;
    }

    public int validateEmail(String email){
        if (!TextUtils.isEmpty(email) && !email.matches(emailRegex)) {
            return 1;
        }

        return 0;
    }

    public int validatePhone(String phone){
        if (!TextUtils.isEmpty(phone) && !phone.matches(phoneRegex)) {
            return 1;
        }

        return 0;
    }

    public int validateBirthdate(String birthDate){
        if (TextUtils.isEmpty(birthDate)){
            return 0;
        }

        Date parsedBirthDate;

        try{
            parsedBirthDate = dateFormatter.parse(birthDate);
        } catch (ParseException e){
            return 1;
        }

        if (parsedBirthDate.after(Calendar.getInstance().getTime())){
            return 2;
        }

        return 0;
    }
}
