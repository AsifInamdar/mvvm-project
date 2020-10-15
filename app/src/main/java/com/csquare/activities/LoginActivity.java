package com.csquare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.csquare.PrefManager;
import com.csquare.SquareUtil;
import com.csquare.data.LoginViewModel;
import com.csquare.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!new PrefManager(this).getToken().isEmpty()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginViewModel = new LoginViewModel(this);
        binding.setLoginview(loginViewModel);

        binding.buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoginValid()) {
                    loginViewModel.login(binding.etEmail.getText().toString(), binding.etPassword.getText().toString(), LoginActivity.this);
                }
            }
        });

        loginViewModel.isLoginSuccess.observe(this, isLoginSuccess -> {
            if (isLoginSuccess) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private boolean isLoginValid() {
        if (TextUtils.isEmpty(binding.etEmail.getText()) || TextUtils.isEmpty(binding.etPassword.getText())) {
            Toast.makeText(this, "Please enter email & password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!SquareUtil.isValidEmail(binding.etEmail.getText().toString())) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}