package com.example.heba.testproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private Intent myintent;
    private Handler mHandler = new Handler();
    private int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.LoadingProgress);
        mTextView = (TextView) findViewById(R.id.LoadingText);
        mProgressBar.getProgressDrawable().setColorFilter(
                Color.rgb(56, 142, 60), android.graphics.PorterDuff.Mode.SRC_IN);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    while (mProgressStatus < 100) {
                        sleep(30); //Delay of .5 second
                        mProgressStatus++;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mProgressStatus >= 25 && mProgressStatus < 50)
                                    mTextView.setText("Loading..");
                                else if (mProgressStatus >= 50 && mProgressStatus < 75)
                                    mTextView.setText("Loading...");
                                else if (mProgressStatus >= 75 && mProgressStatus <= 100)
                                    mTextView.setText("Loading....");
                                mProgressBar.setProgress(mProgressStatus);
                            }
                        });
                    }
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(MainActivity.this,
                            ChooseActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }

}
