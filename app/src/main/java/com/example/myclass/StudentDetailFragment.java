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

import com.example.myclass.Model.Student;
import com.example.myclass.Repository.GetStudentByIDAsync;
import com.example.myclass.Repository.callBackInteface;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDetailFragment extends Fragment {
    View view;
    TextView studentDetID,Detname,Detage,Detsex,Detdate;
    Button upDatebutton,cancelbutton;
    int curItem;

    public StudentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       curItem = getArguments().getInt("currentClick");
       view= inflater.inflate(R.layout.fragment_student_detail, container, false);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        studentDetID= view.findViewById(R.id.studentDetID);
        Detname= view.findViewById(R.id.Detname);
       Detage= view.findViewById(R.id.Detage);
        Detsex= view.findViewById(R.id.Detsex);
        Detdate= view.findViewById(R.id.Detdate);

        upDatebutton= view.findViewById(R.id.upDatebutton);
        cancelbutton= view.findViewById(R.id.cancelbutton);

                new GetStudentByIDAsync(getActivity(), curItem, new callBackInteface<Student>() {
                    @Override
                    public void callBackData(Student response) {
                        studentDetID.setText("Roll Number is : "+ Integer.toString(response.getRollnumber()));
                        Detname.setText("Name of the Student is : "+response.getName());
                        Detage.setText("Age of the Student is : "+Integer.toString(response.getAge()));
                        Detsex.setText("Sex of the Student is : "+response.getSex());
                        Detdate.setText("Date of Joining is : "+response.getDate());

                    }

                    @Override
                    public void callBackException(Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).execute();
                cancelbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().beginTransaction().remove(StudentDetailFragment.this).commit();
                    }
                });

        upDatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("currentSelected",curItem);
                UpdateStudentFragment updateStudentFragment=new UpdateStudentFragment();
                updateStudentFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,updateStudentFragment).commit();
            }
        });
            }


}
