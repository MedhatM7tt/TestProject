package com.example.heba.testproject;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegActivity extends AppCompatActivity {

    String mPasswordConfirm, mPassword, mID, mEmail;
    EditText mPasswordConfirmE, mPasswordE, mIDE, mEmailE;
    Button regBtn;
    AlertDialog.Builder builder;
    String regUrl = "http://192.168.1.102/Project/StudentReg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        //mPassword=mPasswordConfirm="";
        regBtn = (Button) findViewById(R.id.SubmitButton);
        mEmailE = (EditText) findViewById(R.id.studentEmail);
        mPasswordConfirmE = (EditText) findViewById(R.id.studentPassRegConfirm);
        mPasswordE = (EditText) findViewById(R.id.studentPassReg);
        mIDE = (EditText) findViewById(R.id.studentIdReg);
        mPasswordConfirmE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword = mPasswordE.getText().toString();
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mID = mIDE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPassword.trim().length() == 0 || mID.trim().length() != 14 || mPassword.length() < 8) {
                    regBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword = mPasswordE.getText().toString();
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mID = mIDE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPassword.trim().length() == 0 || mID.trim().length() != 14 || mPassword.length() < 8) {
                    regBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();

            }
        });
        mPasswordE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                mID = mIDE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPasswordConfirm.equals("") || mID.trim().length() != 14 || mPassword.length() < 8) {
                    regBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                mID = mIDE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPasswordConfirm.equals("") || mID.trim().length() != 14 || mPassword.length() < 8) {
                    regBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        regBtn.setEnabled(true);
                    else
                        regBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPassword = mPasswordE.getText().toString();

            }
        });
        mIDE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                mID = mIDE.getText().toString();
                if (mID.length() != 14 || mPassword.equals("") || mPasswordConfirm.equals("") || !mPassword.equals(mPasswordConfirm) || mPassword.length() < 8)
                    regBtn.setEnabled(false);
                else
                    regBtn.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                mID = mIDE.getText().toString();
                if (mID.length() != 14 || mPassword.equals("") || mPasswordConfirm.equals("") || !mPassword.equals(mPasswordConfirm) || mPassword.length() < 8)
                    regBtn.setEnabled(false);
                else
                    regBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mID = mIDE.getText().toString();
            }
        });

        builder = new AlertDialog.Builder(RegActivity.this);
        //**************************************************//

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmail = mEmailE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mID = mIDE.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, regUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            builder.setTitle("Response");
                            builder.setMessage(message);
                            displayAlert(code);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        builder.setTitle("Response");
                        builder.setMessage("Error on Connection !");
                        displayAlert("Fail");
                        Log.d("Response", "" + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("ID", mID);
                        params.put("Password", mPassword);
                        params.put("Email", mEmail);
                        return params;
                    }
                };
                MySingleton.getmInstance(RegActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }

    private void displayAlert(final String code) {
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (code.equals("Done")||code.equals("AlreadyCreated")) {
                    finish();
                } else if (code.equals("Fail")) {
                    mPasswordConfirmE.setText("");
                    mPasswordE.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
