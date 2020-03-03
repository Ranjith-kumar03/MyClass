package com.example.myclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myclass.Model.Student;
import com.example.myclass.Repository.GetAllStudentAsync;
import com.example.myclass.Repository.callBackInteface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.callBackClickEvent {
    FragmentManager manager=this.getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

final static String TAG=MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("             My Tution Class ");
        actionBar.setIcon(R.drawable.students);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        GetStudentFragment getStudent=new GetStudentFragment();
        fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, getStudent).commit();

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        fragmentTransaction=manager.beginTransaction();
        AddStudentFragment addStudent=new AddStudentFragment();
        UpdateStudentFragment updateStudent=new UpdateStudentFragment();
        GetStudentFragment getStudent=new GetStudentFragment();
        switch(item.getItemId())
        {

            case R.id.addNewStudent:


                    fragmentTransaction.replace(R.id.fragmentContainer,addStudent);
                fragmentTransaction.commit();
                break;


            case R.id.Exit:
                finish();
                moveTaskToBack(true);
                break;



        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickEvent(int i) {
        fragmentTransaction=manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("currentClick", i+1);
        StudentDetailFragment studentDetailFragment=new StudentDetailFragment();
        studentDetailFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragmentContainer,studentDetailFragment);
        fragmentTransaction.commit();
    }
}




