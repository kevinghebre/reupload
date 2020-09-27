package com.kelompok_b.petshop.data_management.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kelompok_b.petshop.data_management.model.User;


@Database(entities = {User.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}


