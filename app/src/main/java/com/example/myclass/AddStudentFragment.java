package com.example.myclass;

import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myclass.Model.Student;
import com.example.myclass.Repository.CreateStudentAsync;
import com.example.myclass.Repository.callBackInteface;
import com.google.android.material.textfield.TextInputEditText;


public class AddStudentFragment extends Fragment {

    View view;
    TextInputEditText addname,addage,adddate;
    Button addbutton;
    GetStudentFragment getStudent=new GetStudentFragment();
    AutoCompleteTextView addsex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=inflater.inflate(R.layout.fragment_add_student, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addname=view.findViewById(R.id.addname);
        addage=view.findViewById(R.id.addage);
        addsex=view.findViewById(R.id.addsex);
        adddate=view.findViewById(R.id.adddate);
        addbutton=view.findViewById(R.id.addbutton);
        String [] gender={"Male","Female","DoesNotWantToIdentify"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, gender);
        addsex.setThreshold(1);
        addsex.setAdapter(adapter);


       final Student  student=new Student();
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addname.getText().toString().isEmpty()||addage.getText().toString().isEmpty()||addsex.getText().toString().isEmpty()||adddate.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Please enter all the Fields", Toast.LENGTH_SHORT).show();
                }else
                {

                    student.setName(addname.getText().toString());
                    student.setAge(Integer.parseInt(addage.getText().toString()));
                    student.setSex(addsex.getText().toString());
                    student.setDate(adddate.getText().toString());
                    new CreateStudentAsync(getContext(),student, new callBackInteface<Student>() {

                        @Override
                        public void callBackData(Student response) {
                            //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,getStudent).commit();
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


}
