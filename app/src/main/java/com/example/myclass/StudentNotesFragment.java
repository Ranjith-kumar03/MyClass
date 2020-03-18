package com.example.myclass;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclass.Model.StudentData;
import com.example.myclass.Model.StudentwithData;
import com.example.myclass.Repository.callBackInterfaceUpdateStudentData;
import com.example.myclass.Repository.getAsynchStudentData;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentNotesFragment extends Fragment {
    View view;
    TextView studentDetnotesInfoID;
    Button cancelfobutton;
    int curItem;
    StringBuilder sb=new StringBuilder();
    public StudentNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_student_notes, container, false);
        curItem=  getArguments().getInt("currentSelected", 0);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        studentDetnotesInfoID= view.findViewById(R.id.studentDetnotesInfoID);
        cancelfobutton= view.findViewById(R.id.cancelfobutton);
        //Toast.makeText(getActivity(), "Current Student is : "+curItem, Toast.LENGTH_SHORT).show();
        new getAsynchStudentData(getActivity(), new callBackInterfaceUpdateStudentData<List<StudentwithData>>() {


            @Override
            public void callBackStudentData(List<StudentwithData> response) {
                if(response!=null) {
                    for (StudentwithData x : response) {

                        sb.append("Student Name : "+ x.getStudent().getName().toString()+"\n"+"\n");
                        for(StudentData y:x.getStudentData) {
                            sb.append("Date : " + y.getTime() + "\n" + "Added Notes : " + y.getData() + "\n" + "\n");
                        }


                    }
                    studentDetnotesInfoID.setText(sb.toString());
                }else
                {
                    Toast.makeText(getContext(), "No Data About that Student is Added  Thankyou", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void callBackStudentDataException(Exception e) {

            }
        }, curItem).execute();
        cancelfobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetStudentFragment getStudentFragment=new GetStudentFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,getStudentFragment).commit();
            }
        });
    }


}
