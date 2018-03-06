package com.example.heba.testproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Subject_StudentActivity extends AppCompatActivity {
    private String Acc;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__student);
        if (!SharedPrefManager.getmInstance(this).isLogged()) {
            finish();
        }

        ArrayList<SubjectData> subjects ;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait until fetching your data...");
        progressDialog.show();
        Acc = SharedPrefManager.getmInstance(this).getUserAcc();
        String subjectList = SharedPrefManager.getmInstance(this).getSubjectList();
        subjectList=subjectList.substring(1,subjectList.length()-1);
        if (subjectList.equals(""))
            Toast.makeText(this, "Please Check your data in the department... !", Toast.LENGTH_LONG).show();
        else {
            subjects = makeList(subjectList);
            final ListView listView = (ListView) findViewById(R.id.list);
            SubjectsAdapter subjectsAdapter = new SubjectsAdapter(this, subjects);
            listView.setAdapter(subjectsAdapter);
        }
        progressDialog.dismiss();
    }

    private ArrayList<SubjectData> makeList(String list){
        ArrayList<SubjectData> temp=new ArrayList<>();
        String [] array=list.split(",");
        for(int i=0;i<array.length;) {
            SubjectData subjectData = new SubjectData();
            subjectData.setSubjectCode(array[i++].trim());
            subjectData.setDoneEval1(array[i++].trim());
            subjectData.setDoneEval2(array[i++].trim());
            subjectData.setActiveEval1(array[i++].trim());
            subjectData.setActiveEval2(array[i++].trim());
            temp.add(subjectData);
        }
        return temp;
    }
}
