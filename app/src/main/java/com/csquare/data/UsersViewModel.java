package com.csquare.data;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.csquare.SquareUtil;
import com.csquare.api.NetworkUtil;
import com.csquare.api.RestClient;
import com.csquare.api.pojos.UsersListResponse;
import com.csquare.roomDatabase.UsersRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersViewModel extends ViewModel {

    public ObservableInt progressBar;
    public MutableLiveData<UserUpdatePojo> users = new MutableLiveData<>();
    UsersRepository usersRepository;

    public void getUsers(int page, Context context) {

        if (!NetworkUtil.isConnectedToInternet(context)) {
            Toast.makeText(context, SquareUtil.NO_INTERNET_TEXT, Toast.LENGTH_SHORT).show();
            return;
        }
        RestClient.getAPIService().getUsers(SquareUtil.USERS_API + page)
                .enqueue(new Callback<UsersListResponse>() {
                    @Override
                    public void onResponse(Call<UsersListResponse> call, Response<UsersListResponse> response) {
                        if (response.isSuccessful()) {
                            List<User> userList = new ArrayList<>();
                            for (User user : response.body().getData()) {
                                if (getVowelCount(user.getFirstName() + user.getLastName()) > 4) {
                                    userList.add(new User(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                                            user.getAvatar()));
                                }
                            }

                            usersRepository = new UsersRepository(context);
                            usersRepository.insertAll(userList);

                            users.postValue(new UserUpdatePojo(response.body().getTotal(), userList));
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersListResponse> call, Throwable t) {

                    }
                });
    }

    private int getVowelCount(String name) {
        int vowels = 0;
        for (int i = 0; i < name.length(); ++i) {
            char ch = name.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i'
                    || ch == 'o' || ch == 'u') {
                ++vowels;
            }
        }
        return vowels;
    }
}
