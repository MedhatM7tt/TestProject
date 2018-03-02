package com.example.heba.testproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentLoginActivity extends AppCompatActivity {
    private String mStudentAcc,mStudentPass;
    private EditText mStudentAccE,mStudentPassE;
    private Button mLoginBtn;
    private ProgressDialog progressDialog;
    private CheckBox mShowPass;
    private AlertDialog.Builder builder;
    private Set<String> set = new HashSet<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        if(SharedPrefManager.getmInstance(this).isLogged()){
            finish();
            startActivity(new Intent(this,Subject_StudentActivity.class));
            return;
        }
        mStudentAccE=(EditText) findViewById(R.id.studentAcc);
        mStudentPassE=(EditText) findViewById(R.id.studentPass);
        mLoginBtn = (Button) findViewById(R.id.studentLogin);
        mStudentAcc=mStudentPass="" ;
        progressDialog  = new ProgressDialog(this);
        mShowPass = (CheckBox)findViewById(R.id.showPass);
        mShowPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    mStudentPassE.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{
                    mStudentPassE.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
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
                progressDialog.setMessage("Signing in...");
                progressDialog.show();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.STUDENT_LOGIN_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            for(int count=1;count<jsonArray.length();count++){
                                JSONObject jsonObject2 = jsonArray.getJSONObject(count);
                                set.add(jsonObject2.getString("subjectCode").toString());
                            }
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
        progressDialog.dismiss();
        if(code.equals("Success")){
            SharedPrefManager.getmInstance(getApplicationContext()).userLogin(mStudentAcc,set);
            startActivity(new Intent(StudentLoginActivity.this,Subject_StudentActivity.class));
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
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
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
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }

    public void reg(View view) {
        startActivity(new Intent(this, StrudentRegActivity.class));
    }
}
