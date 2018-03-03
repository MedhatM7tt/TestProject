package com.example.heba.testproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
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

        ArrayList<String> subjects = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait until fetching your data...");
        progressDialog.show();
        Acc = SharedPrefManager.getmInstance(this).getUserAcc();
        Set<String> subjectSet = SharedPrefManager.getmInstance(this).getSubjectSet();
        if (subjectSet.isEmpty())
            Toast.makeText(this, "Please Check your data in the department... !", Toast.LENGTH_LONG).show();
        else {
            for (String s : subjectSet) {
                subjects.add(s);
            }
            final ListView listView = (ListView) findViewById(R.id.list);
            SubjectsAdapter subjectsAdapter = new SubjectsAdapter(this, subjects);
            listView.setAdapter(subjectsAdapter);
        }
        progressDialog.dismiss();
    }
}
