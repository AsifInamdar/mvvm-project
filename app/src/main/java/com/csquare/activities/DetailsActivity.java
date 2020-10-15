package com.csquare.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.csquare.databinding.ActivityDetailsBinding;
import com.csquare.roomDatabase.RoomDBClient;
import com.csquare.roomDatabase.Users;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int id = getIntent().getIntExtra("id", 0);

        Users user = RoomDBClient.getInstance(this).getAppDatabase().usersDao().getUser(id);

        binding.setUser(user);
    }
}