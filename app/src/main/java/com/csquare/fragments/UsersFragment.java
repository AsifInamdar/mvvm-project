package com.csquare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csquare.adapters.UsersAdapter;
import com.csquare.data.User;
import com.csquare.data.UsersViewModel;
import com.csquare.databinding.FragmentUsersBinding;
import com.csquare.roomDatabase.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    FragmentUsersBinding binding;
    UsersViewModel usersViewModel;
    int pageNumber = 1, preLast = -1, totalCount = 0;
    LinearLayoutManager layoutManager;
    List<User> userList = new ArrayList<>();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        getUsers();

        layoutManager = new LinearLayoutManager(getActivity());

        UsersAdapter adapter = new UsersAdapter(getActivity());
        binding.rvUsers.setLayoutManager(layoutManager);
        binding.rvUsers.setAdapter(adapter);

        usersViewModel.users.observe(requireActivity(), userModel -> {
            totalCount = userModel.getTotalCount();
            userList.addAll(userModel.getUsers());
            adapter.setUsers(userModel.getUsers());
            adapter.notifyItemRangeChanged(0, userList.size());
            binding.progess.setVisibility(View.GONE);

            binding.tvUserCount.setText(String.valueOf(userList.size()));
        });


        binding.rvUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = binding.rvUsers.getLayoutManager().getChildCount();
                int totalItemCount = binding.rvUsers.getLayoutManager().getItemCount();

                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                final int lastItem = pastVisibleItems + visibleItemCount;
                if (lastItem == totalItemCount) {
                    if (adapter == null)
                        return;
                    if (adapter.getItemCount() == 0)
                        return;
                    if (userList.size() == 0)
                        return;
                    if (preLast != lastItem) { //to avoid multiple calls for last item
                        preLast = lastItem;
                        if (totalItemCount < totalCount) {
                            pageNumber++;
                            getUsers();
                        }
                    }
                }
            }
        });

    }

    private void getUsers() {
        binding.progess.setVisibility(View.VISIBLE);
        usersViewModel.getUsers(pageNumber, getActivity());
    }

}