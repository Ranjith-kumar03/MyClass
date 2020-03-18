package com.example.myclass.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myclass.Model.Connection;
import com.example.myclass.Model.StudentData;

public class CreateAsynchStudentDataUpdate extends AsyncTask<Integer,Void, StudentData> {
    private static final String TAG = "CreateAsynchStudentData";
      private Context context;
      private StudentData studentdata;
      private callBackInterfaceUpdateStudentData<StudentData> callback;
      private Exception e;

    public CreateAsynchStudentDataUpdate(Context context, StudentData studentdata, callBackInterfaceUpdateStudentData<StudentData> callback) {
        this.context = context;
        this.studentdata = studentdata;
        this.callback = callback;
    }

    @Override
    protected StudentData doInBackground(Integer... integers) {
        try {
            Connection.getInstance(context).getDatabase().getStudentDataDAO().addStudentData(studentdata);
        }catch(Exception ex)
        {
            e=ex;
        }
        return studentdata;
    }

    @Override
    protected void onPostExecute(StudentData studentData) {
        super.onPostExecute(studentData);
        if(callback!=null)
        {
            if(e==null)
            {
                callback.callBackStudentData(studentData);
                Log.d(TAG, "onPostExecute: "+studentData.getStudentId());
                Log.d(TAG, "onPostExecute: "+studentData.getTime());
                Log.d(TAG, "onPostExecute: "+studentData.getData());
            }else
            {
                callback.callBackStudentDataException(e);
            }
        }
    }
}
