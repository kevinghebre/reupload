package com.kelompok_b.petshop.data_management.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kevinghebre.datapersistent_unguided_9774.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}


