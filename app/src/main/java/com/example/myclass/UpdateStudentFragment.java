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
import com.example.myclass.Repository.GetStudentByIDAsync;
import com.example.myclass.Repository.UpdateStudentAsync;
import com.example.myclass.Repository.callBackInteface;
import com.google.android.material.textfield.TextInputEditText;


public class UpdateStudentFragment extends Fragment {
    View view;
    int cursel;
    Student student=new Student();
    TextInputEditText updateage,updatesex,updatedate;
    EditText updatename;
    Button updatebutton,cancelbutton;
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
        updatebutton= view.findViewById(R.id.updatebutton);
        cancelbutton= view.findViewById(R.id.cancelbutton);

        new GetStudentByIDAsync(getActivity(), cursel, new callBackInteface<Student>() {
            @Override
            public void callBackData(Student response) {
                student=response;

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

                            Log.d("UpdateStudentAsync", student.getName());
                            Log.d("UpdateStudentAsync", Integer.toString(student.getAge()));
                            Log.d("UpdateStudentAsync", student.getSex());
                            Log.d("UpdateStudentAsync", Integer.toString(student.getRollnumber()));
                            new UpdateStudentAsync(getActivity(), student, new callBackInteface<Student>() {
                                @Override
                                public void callBackData(Student response) {
                                    Toast.makeText(getContext(), response.getName() + " details are updated", Toast.LENGTH_SHORT).show();
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
                getFragmentManager().beginTransaction().remove(UpdateStudentFragment.this).commit();
            }
        });


    }
}