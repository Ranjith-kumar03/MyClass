package com.example.myclass.Model;

import android.content.Context;

import androidx.room.Room;

public class Connection {
    public static Connection instance;
    public AppDatabase database;

    public Connection(Context context)
    {
        database= Room.databaseBuilder(context, AppDatabase.class,"MyDatabase").build();
    }

    public static  Connection getInstance(Context context)
    {
        synchronized (Connection.class)
        {
            if(instance==null)
            {
                instance=new Connection(context);
            }
        }
        return instance;
    }

    public AppDatabase getDatabase()
    {
        return database;
    }

}
