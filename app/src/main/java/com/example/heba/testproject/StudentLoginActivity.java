package com.example.heba.testproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentLoginActivity extends AppCompatActivity {
    private String mStudentAcc,mStudentPass;
    private EditText mStudentAccE,mStudentPassE;
    private Button mLoginBtn;
    AlertDialog.Builder builder;
    String studentLoginUrl = "http://mhtt.000webhostapp.com/Project/StudentLogin.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        mStudentAccE=(EditText) findViewById(R.id.studentAcc);
        mStudentPassE=(EditText) findViewById(R.id.studentPass);
        mLoginBtn = (Button) findViewById(R.id.studentLogin);
        mStudentAcc=mStudentPass="" ;

        mStudentAccE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
                if(mStudentPass.length()>=8&&mStudentAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
                if(mStudentPass.length()>=8&&mStudentAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();

            }
        });

        mStudentPassE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
                if(mStudentPass.length()>=8&&mStudentAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
                if(mStudentPass.length()>=8&&mStudentAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
            }
        });
        /***********************************************/
        builder = new AlertDialog.Builder(StudentLoginActivity.this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStudentAcc=mStudentAccE.getText().toString();
                mStudentPass=mStudentPassE.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, studentLoginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            displayAlert(jsonObject.getString("code"),jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        displayAlert("Fail","Connection Error !");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String , String> params=new HashMap<String, String>();
                        params.put("Acc",mStudentAcc);
                        params.put("Password",mStudentPass);
                        return params;
                    }
                };
                MySingleton.getmInstance(StudentLoginActivity.this).addToRequestQueue(stringRequest);
            }
        });

    }

    public void displayAlert(String code , String message){
        if(code.equals("Success")){
            startActivity(new Intent(StudentLoginActivity.this,SubjectsActivity.class));
            finish();
        }
        else if(message.equals("Wrong Password!")){
            builder.setTitle("Login Error !");
            builder.setMessage(message);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mStudentPassE.setText("");
                }
            });
        }
        else{
            builder.setTitle("Login Error !");
            builder.setMessage(message);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mStudentAccE.setText("");
                    mStudentPassE.setText("");
                }
            });
        }
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void reg(View view) {
        startActivity(new Intent(this, RegActivity.class));
    }
}
