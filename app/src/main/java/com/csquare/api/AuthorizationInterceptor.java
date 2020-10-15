package com.csquare.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private volatile String authorization;

    public AuthorizationInterceptor(String authorization) {
        this.authorization = authorization;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        if (this.authorization != null) {
            request = request.newBuilder()
                    .header("authorization", "Bearer " + this.authorization)
                    .build();
        }
        return chain.proceed(request);
    }
}
