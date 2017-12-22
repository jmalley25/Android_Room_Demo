package com.example.jlmalley.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by jlmalley on 12/21/2017.
 */

//Version must be updated when the database structure is changed.
@Database( entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
