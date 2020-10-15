package com.csquare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csquare.adapters.UsersAdapter;
import com.csquare.data.UsersViewModel;
import com.csquare.databinding.ActivityDetailsBinding;
import com.csquare.databinding.FragmentUsersBinding;
import com.csquare.roomDatabase.RoomDBClient;
import com.csquare.roomDatabase.Users;
import com.csquare.roomDatabase.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends Fragment {

    ActivityDetailsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = ActivityDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = getArguments().getInt("id");

        Users user = RoomDBClient.getInstance(getActivity()).getAppDatabase().usersDao().getUser(id);

        binding.setUser(user);
    }

}