package com.example.myclass;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclass.Model.Student;
import com.example.myclass.Repository.GetAllStudentAsync;
import com.example.myclass.Repository.callBackInteface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class GetStudentFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    public GetStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_get_student, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerID);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        new GetAllStudentAsync(this.getActivity(), new callBackInteface<List<Student>>() {

            @Override
            public void callBackData(List<Student> response) {
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < response.size(); i++) {
                    sb.append(response.get(i).getName());

                }
                //Toast.makeText(getActivity(), sb, Toast.LENGTH_SHORT).show();
                adapter=new StudentAdapter(response,getActivity());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
    }


            @Override
            public void callBackException(Exception e) {
                Toast.makeText(getActivity(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }).execute();




    }



}

