package com.example.myclass.Repository;

public interface callBackInteface<T> {
    void callBackData(T response);
    void callBackException(Exception e);
}
