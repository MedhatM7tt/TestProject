package com.example.heba.testproject;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegActivity extends AppCompatActivity {

    String mUserName,mPassword;
    EditText mUserNameE,mPasswordE;
    Handler mHandler=new Handler();
    Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mUserName=mPassword="";
        regBtn =(Button)findViewById(R.id.SubmitButton);
        mUserNameE=(EditText)findViewById(R.id.studentNameReg);
        mPasswordE=(EditText)findViewById(R.id.studentPassReg);
        mUserNameE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword=mPasswordE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPassword.trim().length()==0){
                    regBtn.setEnabled(false);
                }
                else{
                    regBtn.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword=mPasswordE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPassword.trim().length()==0){
                    regBtn.setEnabled(false);
                }
                else{
                    regBtn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPasswordE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUserName=mUserNameE.getText().toString();
                if(charSequence.toString().trim().length()==0||mUserName.equals("")){
                    regBtn.setEnabled(false);
                }
                else{
                    regBtn.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUserName=mUserNameE.getText().toString();
                if(charSequence.toString().trim().length()==0||mUserName.equals("")){
                    regBtn.setEnabled(false);
                }
                else{
                    regBtn.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*new Thread(new Runnable() {
            @Override
            public void run() {/*
                mUserNameE=(EditText)findViewById(R.id.studentNameReg);
                mPasswordE=(EditText)findViewById(R.id.studentPassReg);
                mUserName=mUserNameE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mUserNameE=(EditText)findViewById(R.id.studentNameReg);
                        mPasswordE=(EditText)findViewById(R.id.studentPassReg);
                        mUserName=mUserNameE.getText().toString();
                        mPassword=mPasswordE.getText().toString();
                        if(!mUserName.equals("")&&!mPassword.equals("")){
                            regBtn.setClickable(true);
                        }
                        else{
                            regBtn.setClickable(false);
                        }
                    }
                });
            }
        }).start();*/
        /*mHandler.post(new Runnable() {
            @Override
            public void run() {
                mUserNameE=(EditText)findViewById(R.id.studentNameReg);
                mPasswordE=(EditText)findViewById(R.id.studentPassReg);
                mUserName=mUserNameE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                if(mUserName.equals(" ")||mPassword.equals(" ")){
                    regBtn.setClickable(false);
                }
                else{
                    regBtn.setClickable(true);
                }
            }
        });*/

    }
}
