package com.example.myclass.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class,StudentData.class},version = 1,exportSchema = true)
public abstract class  AppDatabase extends RoomDatabase {
    public abstract StudentDAO getStudentDAO();
    public abstract StudentDataDAO getStudentDataDAO();
}
