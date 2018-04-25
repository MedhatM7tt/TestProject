package com.example.heba.testproject;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

public class ChangePassActivity extends AppCompatActivity {
    private String mPasswordConfirm, mPassword;
    private EditText mPasswordConfirmE, mPasswordE;
    private Button changeBtn;
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        changeBtn = (Button) findViewById(R.id.ChangeBtn);
        mPasswordConfirmE = (EditText) findViewById(R.id.studentPassChangeConfirm);
        mPasswordE = (EditText) findViewById(R.id.studentPassChange);

        mPasswordConfirmE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword = mPasswordE.getText().toString();
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPassword.trim().length() == 0 || mPassword.length() < 8) {
                    changeBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        changeBtn.setEnabled(true);
                    else
                        changeBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPassword = mPasswordE.getText().toString();
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPassword.trim().length() == 0 || mPassword.length() < 8) {
                    changeBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        changeBtn.setEnabled(true);
                    else
                        changeBtn.setEnabled(false);
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
                if (charSequence.toString().trim().length() == 0 || mPasswordConfirm.equals("")  || mPassword.length() < 8) {
                    changeBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        changeBtn.setEnabled(true);
                    else
                        changeBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordConfirm = mPasswordConfirmE.getText().toString();
                mPassword = mPasswordE.getText().toString();
                if (charSequence.toString().trim().length() == 0 || mPasswordConfirm.equals("") || mPassword.length() < 8) {
                    changeBtn.setEnabled(false);
                } else {
                    if (mPassword.equals(mPasswordConfirm))
                        changeBtn.setEnabled(true);
                    else
                        changeBtn.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPassword = mPasswordE.getText().toString();

            }
        });
        progressDialog  = new ProgressDialog(this);

        builder = new AlertDialog.Builder(ChangePassActivity.this);

        /**************************************************************************/

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Please Wait ... !");
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.CHANGE_PASS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);

                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String message = jsonObject.getString("message");
                            displayAlert(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        MyToast.viewToast("Error In Connection .. !",ChangePassActivity.this);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params = new HashMap<>();
                        params.put("newPassword",mPassword);
                        params.put("type", SharedPrefManager.getmInstance(getApplicationContext()).getType());
                        params.put("Acc",SharedPrefManager.getmInstance(getApplicationContext()).getUserAcc());
                        return params;
                    }
                };
                MySingleton.getmInstance(ChangePassActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }


    private void displayAlert(String message){
        progressDialog.dismiss();
        if(message.equals("Done")){
            MyToast.viewToast("Done !",this);
            SharedPrefManager.getmInstance(this).changePass(mPassword);
            finish();
        }
        else{
            MyToast.viewToast("Failed please Try Again ",this);
        }
    }
}
