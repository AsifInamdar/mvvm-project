package com.csquare;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context mContext;
    SharedPreferences sharedpreferences;

    public PrefManager(Context mContext) {
        this.mContext = mContext;
        sharedpreferences = mContext.getSharedPreferences("square_pref",
                Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken(){
        return sharedpreferences.getString("token", "");
    }

}
