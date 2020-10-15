package com.csquare.api;


import com.csquare.SquareUtil;
import com.csquare.api.pojos.LoginInputPojo;
import com.csquare.api.pojos.LoginResponse;
import com.csquare.api.pojos.UsersListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIServices {

    // login
    @POST(SquareUtil.LOGIN_API)
    Call<LoginResponse> login(@Body LoginInputPojo loginInputPojo);

    // users list
    @GET()
    Call<UsersListResponse> getUsers(@Url String url);
}
