package com.example.myclass.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myclass.Model.Connection;
import com.example.myclass.Model.Student;

import java.util.List;

public class GetAllStudentAsync extends AsyncTask<Integer,Void, List<Student>> {
    private Context context;
    private List<Student> students;
    private callBackInteface<List<Student>> callBack;
    private Exception exception;

    public GetAllStudentAsync(Context context, callBackInteface<List<Student>> callBack) {
        this.context = context;

        this.callBack = callBack;
    }



    @Override
    protected List<Student> doInBackground(Integer... integers) {
       try {
         students=  Connection.getInstance(context).getDatabase().getStudentDAO().getAllStudent();

       }
       catch(Exception e)
       {
           exception=e;
       }
        return students;
    }

    @Override
    protected void onPostExecute(List<Student> students) {
        super.onPostExecute(students);
        if(callBack!=null)
        {
            if(exception==null)
            {
                callBack.callBackData(students);
            }else
            {
                callBack.callBackException(exception);
            }
        }
    }
}
