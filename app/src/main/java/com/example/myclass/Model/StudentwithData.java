package com.example.myclass.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StudentwithData {
    @Embedded
   private Student student;

    @Relation(parentColumn = "rollnumber",
            entityColumn = "studentId")
    public List<StudentData> getStudentData;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
