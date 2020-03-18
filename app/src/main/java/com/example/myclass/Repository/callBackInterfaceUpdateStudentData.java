package com.example.myclass.Repository;

import com.example.myclass.Model.StudentData;

public interface callBackInterfaceUpdateStudentData<T> {
    void callBackStudentData(T response);
    void callBackStudentDataException(Exception e);
}
