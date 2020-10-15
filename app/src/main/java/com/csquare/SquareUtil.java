package com.csquare;

import android.text.TextUtils;
import android.util.Patterns;

public class SquareUtil {

    public static final String BASE_URL = "https://reqres.in/api/";

    public static final String LOGIN_API = "login";
    public static final String USERS_API = "users?page=";

    public static final String NO_INTERNET_TEXT = "Please enable internet";


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
