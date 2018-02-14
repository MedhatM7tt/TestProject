package com.example.heba.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void doctorClick(View view) {
        Intent i = new Intent(this, DocLoginActivity.class);
        startActivity(i);
    }

    public void studentClick(View view) {
        Intent i = new Intent(this, StudentLoginActivity.class);
        startActivity(i);
    }
}
