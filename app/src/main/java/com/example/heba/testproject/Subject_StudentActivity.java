package com.example.heba.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subject_StudentActivity extends AppCompatActivity {
    private String Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__student);
        ArrayList<String> subjects=new ArrayList<>();
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            Acc=extras.getString("Acc");
            Log.v("Acc1",Acc);
        }
        BackgroundTask backgroundTask=new BackgroundTask(this,Acc);
        subjects=backgroundTask.getSubjectsList();
        final ListView listView= (ListView)findViewById(R.id.list);
        RegistrationAdapter registrationAdapter=new RegistrationAdapter(this,subjects);
        listView.setAdapter(registrationAdapter);
    }
}
