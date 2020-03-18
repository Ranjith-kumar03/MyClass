package com.example.myclass.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myclass.Model.Connection;
import com.example.myclass.Model.StudentData;
import com.example.myclass.Model.StudentwithData;

import java.util.List;

public class getAsynchStudentData extends AsyncTask<Integer,Void, List<StudentwithData>> {
    private Context context;
    private callBackInterfaceUpdateStudentData<List<StudentwithData>> callback;
    private int StudentRollnumber;
    private  List<StudentwithData> studentData;
    private Exception e;

    public getAsynchStudentData(Context context, callBackInterfaceUpdateStudentData<List<StudentwithData>> callback, int studentRollnumber) {
        this.context = context;
        this.callback = callback;
        StudentRollnumber = studentRollnumber;
    }

    @Override
    protected List<StudentwithData> doInBackground(Integer... integers) {
        try {
             studentData = Connection.getInstance(context).getDatabase().getStudentDataDAO().getStudentData(StudentRollnumber);
        }catch(Exception ex)
        {
            e=ex;
        }
        return studentData;
    }

    @Override
    protected void onPostExecute(List<StudentwithData> studentwithData) {
        super.onPostExecute(studentwithData);
        if(callback!=null)
        {
            if(e==null)
            {
                callback.callBackStudentData(studentwithData);
            }else
            {
                callback.callBackStudentDataException(e);
            }
        }
    }
}
