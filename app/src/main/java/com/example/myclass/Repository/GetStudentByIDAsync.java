package com.example.myclass.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.myclass.Model.Connection;
import com.example.myclass.Model.Student;

import java.util.List;

public class GetStudentByIDAsync extends AsyncTask<Integer,Void, Student> {
    private Context context;
    private Student student;
    private int id;
    private callBackInteface<Student> callBack;
    private Exception exception;

    public GetStudentByIDAsync(Context context,int id, callBackInteface<Student> callBack) {
        this.context = context;

        this.callBack = callBack;
        this.id=id;
    }



    @Override
    protected Student doInBackground(Integer... integers) {
       try {

         student=  Connection.getInstance(context).getDatabase().getStudentDAO().getStudent(this.id);

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
