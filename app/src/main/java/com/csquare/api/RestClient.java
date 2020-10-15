package com.csquare.api;

import android.app.Activity;

import com.csquare.SquareUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static APIServices REST_CLIENT;

    public static APIServices getInstance(final Activity context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SquareUtil.BASE_URL)
                .client(OkHttpSingleton.getInstance(context).getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        REST_CLIENT = retrofit.create(APIServices.class);

        return REST_CLIENT;
    }

    public static Retrofit getRetroInstance(){
        return new Retrofit.Builder()
                .baseUrl(SquareUtil.BASE_URL)
                .client(OkHttpSingleton.getInstance().getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIServices getAPIService(){
        return getRetroInstance().create(APIServices.class);
    }

}
