package com.example.heba.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DocLoginActivity extends AppCompatActivity {

    private String mDocAcc,mDocPass;
    private EditText mDocAccE,mDocPassE;
    private Button mLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);

        mDocAccE=(EditText) findViewById(R.id.doctorAcc);
        mDocPassE=(EditText) findViewById(R.id.doctorPass);
        mLoginBtn = (Button) findViewById(R.id.loginBtn);
        mDocAcc=mDocPass="" ;

        mDocAccE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDocAcc=mDocAccE.getText().toString();
                mDocPass=mDocPassE.getText().toString();
                if(mDocPass.length()>=8&&mDocAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDocAcc=mDocAccE.getText().toString();
                mDocPass=mDocPassE.getText().toString();
                if(mDocPass.length()>=8&&mDocAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDocPassE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDocAcc=mDocAccE.getText().toString();
                mDocPass=mDocPassE.getText().toString();
                if(mDocPass.length()>=8&&mDocAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDocAcc=mDocAccE.getText().toString();
                mDocPass=mDocPassE.getText().toString();
                if(mDocPass.length()>=8&&mDocAcc.trim().length()!=0)
                    mLoginBtn.setEnabled(true);
                else
                    mLoginBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void doctorLogin(View view) {
    }
}
