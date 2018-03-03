package com.example.heba.testproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by SM on 3/2/2018.
 */

public class SharedPrefManager {
    private static SharedPrefManager mInstance ;
    private static final String SHARED_PREF_NAME = "mySharedPref";
    private static final String USER_ACC_KEY = "userAcc";
    private static final String USER_PASS="userPass";
    private static final String USER_SUBJECTS = "userSubjects";
    private static Context mCtx;

    private SharedPrefManager(Context context){
        mCtx=context;
    }

    public static synchronized SharedPrefManager getmInstance(Context context){
        if(mInstance==null)
            mInstance=new SharedPrefManager(context);
        return mInstance;
    }

    public boolean userLogin(String Acc, Set<String> subjectList,String pass){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ACC_KEY,Acc);
        editor.putStringSet(USER_SUBJECTS,subjectList);
        editor.putString(USER_PASS,pass);
        editor.apply();
        return true;
    }
    public boolean isLogged(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(USER_ACC_KEY,null)!=null)
            return true;
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        return true;
    }

    public String getUserAcc(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ACC_KEY,null);
    }
    public String getUserPass(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_PASS,null);
    }
    public Set<String> getSubjectSet(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(USER_SUBJECTS,null);
    }
}
