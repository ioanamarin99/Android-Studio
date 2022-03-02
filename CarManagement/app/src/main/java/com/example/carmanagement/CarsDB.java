package com.example.carmanagement;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class, TechInspection.class}, version = 5, exportSchema = false)
public abstract class CarsDB extends RoomDatabase {

    private final static String DB_NAME = "cars.db";
    private static CarsDB instance;

    public static CarsDB getInstance(Context context){
        if(instance==null)
            instance = Room.databaseBuilder(context, CarsDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }

    public abstract CarsDao getCarsDao();
    public abstract TechInspectionsDao getInspectionsDao();
    public abstract CarsInspectionsDao getCarsInspectionsDao();
}
