package com.example.myclass;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclass.Model.Student;
import com.example.myclass.Model.StudentData;
import com.example.myclass.Repository.CreateAsynchStudentDataUpdate;
import com.example.myclass.Repository.GetStudentByIDAsync;
import com.example.myclass.Repository.UpdateStudentAsync;
import com.example.myclass.Repository.callBackInteface;
import com.example.myclass.Repository.callBackInterfaceUpdateStudentData;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;


public class UpdateStudentFragment extends Fragment {
    View view;
    int cursel;
    Student student=new Student();
    StudentData studentData=new StudentData();
    TextInputEditText updateage,updatesex,updatedate,updateNotes;
    EditText updatename;
    TextView updaterollID,timedisplay;
    Button updatebutton,cancelbutton;
    String curTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cursel= getArguments().getInt("currentSelected");
        view= inflater.inflate(R.layout.fragment_update_student, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updatename= view.findViewById(R.id.updatename);
        updateage= view.findViewById(R.id.updateage);
        updatesex= view.findViewById(R.id.updatesex);
        updatedate= view.findViewById(R.id.updatedate);
        updaterollID= view.findViewById(R.id.updaterollID);
        updateNotes= view.findViewById(R.id.updateNotes);
        timedisplay= view.findViewById(R.id.timedisplay);
        updatebutton= view.findViewById(R.id.updatebutton);
        cancelbutton= view.findViewById(R.id.cancelbutton);
        Calendar calendar=Calendar.getInstance();
        String time= DateFormat.getTimeInstance(DateFormat.FULL).format(calendar.getTime());
        String date= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        curTime=time + "\n" + date;


        new GetStudentByIDAsync(getActivity(), cursel, new callBackInteface<Student>() {
            private static final String TAG = "UpdateStudentFragment";
            @Override
            public void callBackData(Student response) {
                student=response;
                updaterollID.setText("You are Updating Roll Number : "+Integer.toString(response.getRollnumber())+" Details");
                updatename.setText(response.getName().toString());
                updateage.setText(Integer.toString(response.getAge()));
                updatesex.setText(response.getSex().toString());
                updatedate.setText(response.getDate().toString());
                studentData.setTime(curTime);
                timedisplay.setText(studentData.getTime());
                studentData.setStudentId(response.getRollnumber());


                updatebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(updatename.getText().toString().isEmpty()||updateage.getText().toString().isEmpty()||updatesex.getText().toString().isEmpty()||updatedate.getText().toString().isEmpty()) {

                            Toast.makeText(getContext(), "Please Make Sure you enter all the fields", Toast.LENGTH_SHORT).show();

                        }else
                        {

                            student.setName(updatename.getText().toString());
                            student.setAge(Integer.parseInt(updateage.getText().toString()));
                            student.setSex(updatesex.getText().toString());
                            student.setDate(updatedate.getText().toString());
                            studentData.setData(updateNotes.getText().toString());

                            Log.d("UpdateStudentAsync", student.getName());
                            Log.d("UpdateStudentAsync", Integer.toString(student.getAge()));
                            Log.d("UpdateStudentAsync", student.getSex());
                            Log.d("UpdateStudentAsync", Integer.toString(student.getRollnumber()));
                            new UpdateStudentAsync(getActivity(), student, new callBackInteface<Student>() {
                                @Override
                                public void callBackData(Student response) {
                                    Toast.makeText(getContext(), response.getName() + " details are updated", Toast.LENGTH_SHORT).show();
                                    GetStudentFragment getStudentFragment=new GetStudentFragment();
                                    new CreateAsynchStudentDataUpdate(getActivity(), studentData, new callBackInterfaceUpdateStudentData<StudentData>() {
                                        @Override
                                        public void callBackStudentData(StudentData response) {
                                           // Toast.makeText(getContext(),"Student Roll Number : "+ " Notes Updated : "+ response.getData(), Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, "onPostExecute: "+response.getStudentId());
                                            Log.d(TAG, "onPostExecute: "+response.getTime());
                                            Log.d(TAG, "onPostExecute: "+response.getData());
                                        }

                                        @Override
                                        public void callBackStudentDataException(Exception e) {
                                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                    }).execute();
                                   // studentData=null;
                                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,getStudentFragment).commit();
                                }

                                @Override
                                public void callBackException(Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }).execute();


                        }
                    }
                });


            }

            @Override
            public void callBackException(Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).execute();

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetStudentFragment getStudentFragment=new GetStudentFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,getStudentFragment).commit();
            }
        });


    }
}
