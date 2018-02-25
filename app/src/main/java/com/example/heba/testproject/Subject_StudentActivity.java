package com.example.heba.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    final private String subjectUrl="http://192.168.1.102/Project/subject.php";
    private String Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__student);
        final ArrayList<String> subjects=new ArrayList<>();
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
            Acc=extras.getString("Acc");
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, subjectUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            int count=0;
                            JSONArray jsonArray=new JSONArray(response);
                            while(count<response.length()) {
                                JSONObject jsonObject = jsonArray.getJSONObject(count);
                                subjects.add(new String(jsonObject.getString("SubjectCode")));
                                count++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Subject_StudentActivity.this,"Error in connection !",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String,String>();
                        params.put("Acc",Acc);
                        return params;
                    }
                };
                MySingleton.getmInstance(Subject_StudentActivity.this).addToRequestQueue(stringRequest);
            }
        }).start();

        final ListView listView= (ListView)findViewById(R.id.list);
        RegistrationAdapter registrationAdapter=new RegistrationAdapter(this,subjects);
        listView.setAdapter(registrationAdapter);
    }
}
