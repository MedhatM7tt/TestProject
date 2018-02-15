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

    String mPasswordConfirm,mPassword,mID;
    EditText mPasswordConfirmE,mPasswordE,mIDE;
    Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //mPassword=mPasswordConfirm="";
        regBtn =(Button)findViewById(R.id.SubmitButton);
        mPasswordConfirmE=(EditText)findViewById(R.id.studentPassRegConfirm);
        mPasswordE=(EditText)findViewById(R.id.studentPassReg);
        mIDE=(EditText)findViewById(R.id.studentIdReg);
        mPasswordConfirmE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword=mPasswordE.getText().toString();
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mID=mIDE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPassword.trim().length()==0||mID.trim().length()!=14||mPassword.length()<8){
                    regBtn.setEnabled(false);
                }
                else{
                    if(mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword=mPasswordE.getText().toString();
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mID=mIDE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPassword.trim().length()==0||mID.trim().length()!=14||mPassword.length()<8){
                    regBtn.setEnabled(false);
                }
                else{
                    if(mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPasswordE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                mID=mIDE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPasswordConfirm.equals("")||mID.trim().length()!=14||mPassword.length()<8){
                    regBtn.setEnabled(false);
                }
                else {
                    if(mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                mID=mIDE.getText().toString();
                if(charSequence.toString().trim().length()==0||mPasswordConfirm.equals("")||mID.trim().length()!=14||mPassword.length()<8){
                    regBtn.setEnabled(false);
                }
                else{
                    if(mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIDE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                mID=mIDE.getText().toString();
                if(mID.length()!=14||mPassword.equals("")||mPasswordConfirm.equals("")||!mPassword.equals(mPasswordConfirm)||mPassword.length()<8)
                    regBtn.setEnabled(false);
                else
                    regBtn.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm=mPasswordConfirmE.getText().toString();
                mPassword=mPasswordE.getText().toString();
                mID=mIDE.getText().toString();
                if(mID.length()!=14||mPassword.equals("")||mPasswordConfirm.equals("")||!mPassword.equals(mPasswordConfirm)||mPassword.length()<8)
                    regBtn.setEnabled(false);
                else
                    regBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
