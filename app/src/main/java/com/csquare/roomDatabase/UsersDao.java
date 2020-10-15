package com.csquare.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.csquare.data.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Users> users);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    Users getUser(int id);
}
