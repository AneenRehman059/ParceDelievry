package com.example.apitask.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apitask.models.ParcelDelivery;


@Database(entities = {ParcelDelivery.class},version = 2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    public static MyDatabase INSTANCE;
    public static MyDatabase getDbInstance(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"DB_NAME")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
