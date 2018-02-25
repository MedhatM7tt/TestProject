package com.example.heba.testproject;

import android.app.VoiceInteractor;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SM on 2/25/2018.
 */

public class BackgroundTask {
    Context ctx;
    ArrayList<String> arrayList=new ArrayList<>();
    final private String subjectUrl="http://192.168.1.102/Project/subject.php";
    String Acc;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    public BackgroundTask(Context ctx, String acc) {
        this.ctx = ctx;
        Acc = acc;
        Log.v("Acc2",Acc);
    }

    public ArrayList<String> getSubjectsList(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, subjectUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for(int count=0;count<jsonArray.length();count++){
                        JSONObject jsonObject = jsonArray.getJSONObject(count);
                        arrayList.add(new String(jsonObject.getString("SubjectCode")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx,"Error in connection !",Toast.LENGTH_SHORT).show();
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

        MySingleton.getmInstance(ctx).addToRequestQueue(stringRequest);

        return arrayList;
    }
}
