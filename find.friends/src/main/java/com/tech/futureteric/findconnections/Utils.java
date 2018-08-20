package com.tech.futureteric.findconnections;

import android.text.TextUtils;
import android.util.Patterns;

public class Utils {

    public static boolean isValidEmail(CharSequence email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        return (!TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches());
    }
}
