package com.example.myclass.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    void addStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Query("SELECT * FROM Student ORDER BY rollnumber")
    List<Student> getAllStudent();

    @Query("SELECT * FROM Student WHERE rollnumber=:roll")
    Student getStudent(int roll);

}
