package com.csquare.api;

import com.csquare.SquareUtil;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return SquareUtil.NO_INTERNET_TEXT;
        // You can send any message whatever you want from here.
    }
}
