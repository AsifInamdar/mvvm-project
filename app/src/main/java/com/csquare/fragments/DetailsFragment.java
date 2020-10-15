package com.csquare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.csquare.databinding.FragmentDetailsBinding;
import com.csquare.roomDatabase.RoomDBClient;
import com.csquare.roomDatabase.Users;

public class DetailsFragment extends Fragment {

    FragmentDetailsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = getArguments().getInt("id");

        Users user = RoomDBClient.getInstance(getActivity()).getAppDatabase().usersDao().getUser(id);

        binding.setUser(user);
    }

}