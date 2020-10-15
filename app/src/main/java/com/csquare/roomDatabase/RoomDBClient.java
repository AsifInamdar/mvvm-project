package com.csquare.roomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class RoomDBClient {

    private Context mCtx;
    private static RoomDBClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private RoomDBClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        RoomDatabase.Builder<AppDatabase> b =
                Room.databaseBuilder(mCtx, AppDatabase.class, "square-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration();

        b.addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
        });

        appDatabase = b.build();
    }

    public static synchronized RoomDBClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new RoomDBClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
