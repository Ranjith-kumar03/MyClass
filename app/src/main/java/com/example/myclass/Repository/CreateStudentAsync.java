package com.example.myclass.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myclass.Model.Connection;
import com.example.myclass.Model.Student;

public class CreateStudentAsync extends AsyncTask<Integer,Void, Student> {
    private Context context;
    private Student student;
    private callBackInteface<Student> callBack;
    private Exception exception;

    public CreateStudentAsync(Context context, Student student, callBackInteface<Student> callBack) {
        this.context = context;
        this.student = student;
        this.callBack = callBack;
    }

    @Override
    protected Student doInBackground(Integer... integers) {
       try {
           Connection.getInstance(context).getDatabase().getStudentDAO().addStudent(student);

       }
       catch(Exception e)
       {
           exception=e;
       }
        return student;
    }

    @Override
    protected void onPostExecute(Student student) {
        super.onPostExecute(student);
        if(callBack!=null)
        {
            if(exception==null)
            {
                callBack.callBackData(student);
            }else
            {
                callBack.callBackException(exception);
            }
        }
    }
}
