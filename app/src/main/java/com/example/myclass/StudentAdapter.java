package com.example.myclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclass.Model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter< StudentAdapter.ViewHolder> {
    private List<Student> students;
    private callBackClickEvent activity;

    public StudentAdapter(List<Student> students, Context context) {
        this.students = students;
        this.activity = (callBackClickEvent)context;
    }

    interface callBackClickEvent{
        void onClickEvent(int i);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView rollNumberTempID,nameTemID,ageTempID,sexTempID,doJTempID;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rollNumberTempID= itemView.findViewById(R.id.rollNumberTempID);
            nameTemID= itemView.findViewById(R.id.nameTemID);
//            ageTempID= itemView.findViewById(R.id.ageTempID);
//            sexTempID= itemView.findViewById(R.id.sexTempID);
//            doJTempID= itemView.findViewById(R.id.doJTempID);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onClickEvent(students.indexOf((Student)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public  StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclertemplate,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  StudentAdapter.ViewHolder holder, int position) {
         holder.itemView.setTag(students.get(position));
        holder.rollNumberTempID.setText(Integer.toString(students.get(position).getRollnumber()));
        holder.nameTemID.setText(students.get(position).getName());
//        holder.ageTempID.setText(Integer.toString(students.get(position).getAge()));
//        holder.sexTempID.setText(students.get(position).getSex());
//        holder.doJTempID.setText(students.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
