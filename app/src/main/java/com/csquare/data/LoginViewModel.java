package com.csquare.data;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.csquare.PrefManager;
import com.csquare.SquareUtil;
import com.csquare.api.NoConnectivityException;
import com.csquare.api.RestClient;
import com.csquare.api.pojos.LoginInputPojo;
import com.csquare.api.pojos.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private Context context;
    public ObservableInt progressBar;
    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public MutableLiveData<Boolean> isLoginSuccess = new MutableLiveData<>();

    public LoginViewModel(Context context) {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
    }

    public void login(String email, String pass, Activity context) {

        progressBar.set(View.VISIBLE);

        LoginInputPojo input = new LoginInputPojo();
        input.setEmail(email);
        input.setPassword(pass);
        RestClient.getInstance(context).login(input)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        progressBar.set(View.GONE);
                        if (response.isSuccessful()) {
                            new PrefManager(context).setToken(response.body().getToken());
                            isLoginSuccess.postValue(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressBar.set(View.GONE);
                        isLoginSuccess.postValue(false);
                        if (t instanceof NoConnectivityException) {
                            showToast(SquareUtil.NO_INTERNET_TEXT);
                        }
                    }
                });
    }

    void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
