package com.csquare.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import com.csquare.data.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {

    private List<Users> users = new ArrayList<>();
    private UsersDao usersDao;

    public UsersRepository(Context context) {
        usersDao = RoomDBClient.getInstance(context).getAppDatabase().usersDao();
    }

    public void insertAll(List<User> userList) {
        for (User user : userList) {
            users.add(new Users(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(),
                    user.getAvatar()));
        }

        new InsertUsersAsync().execute();
    }

    private class InsertUsersAsync extends AsyncTask<List<Users>, Void, Void> {

        @Override
        protected Void doInBackground(List<Users>... lists) {
            usersDao.insertAll(users);
            return null;
        }
    }
}
