package com.example.myclass.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDataDAO {
    @Insert
    void addStudentData(StudentData studentData);

    @Update
    void updateStudentData(StudentData studentData);

    @Transaction
    @Query("SELECT * FROM Student where rollnumber=:studentIDforData")
       List<StudentwithData> getStudentData(int studentIDforData);


}
