package com.example.myclass.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentData {
    @PrimaryKey(autoGenerate =true )
    private int id;

    private String time;
    private  String data;
    private int studentId;

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
