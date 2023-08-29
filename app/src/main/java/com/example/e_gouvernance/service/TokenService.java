package com.example.e_gouvernance.service;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_gouvernance.entity.TokenResponse;

public class TokenService {
    private Context context;
    public TokenService(Context mContext){
        context = mContext;
    }
    public String getToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Session", MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
    public String getUserConected(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Session", MODE_PRIVATE);
        return sharedPreferences.getString("user", "");
    }
}
