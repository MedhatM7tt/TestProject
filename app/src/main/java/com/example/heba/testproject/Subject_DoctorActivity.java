package com.example.heba.testproject;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Subject_DoctorActivity extends android.support.v4.app.Fragment {
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_subject__doctor, container, false);
        if (!SharedPrefManager.getmInstance(getActivity()).isLogged()) {
            getActivity().finish();
        }
        ArrayList<SubjectData> subjects ;
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait until fetching your data...");
        progressDialog.show();

        String subjectList = SharedPrefManager.getmInstance(getActivity()).getSubjectList();

        subjectList=subjectList.substring(1,subjectList.length()-1);

        if(subjectList.equals("")){
            Toast.makeText(getActivity(),"Please Check your data in the department... !",Toast.LENGTH_LONG).show();
        }
        else{
            subjects = makeList(subjectList);
            final ListView listView = (ListView) rootView.findViewById(R.id.listDoc);
            DoctorSubjectAdapter doctorSubjectAdapter = new DoctorSubjectAdapter(getActivity(), subjects);
            listView.setAdapter(doctorSubjectAdapter);
        }
        progressDialog.dismiss();
        return rootView;

    }

    private ArrayList<SubjectData> makeList(String list){
        ArrayList<SubjectData> temp=new ArrayList<>();
        String [] array=list.split(",");
        for(int i=0;i<array.length;) {
            SubjectData subjectData = new SubjectData();
            subjectData.setSubjectCode(array[i++].trim());
            subjectData.setActiveEval1(array[i++].trim());
            subjectData.setActiveEval2(array[i++].trim());
            subjectData.setDoneEval1("");
            subjectData.setDoneEval2("");
            temp.add(subjectData);
        }
        return temp;
    }

}