package com.example.heba.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentLoginActivity extends AppCompatActivity {
    private String mStudentAcc,mStudentPass;
    private EditText mStudentAccE,mStudentPassE;
    private Button mLoginBtn;
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

            }
        });
    }

    public void studentLogin(View view) {

    }

    public void reg(View view) {
        startActivity(new Intent(this, RegActivity.class));
    }
}
