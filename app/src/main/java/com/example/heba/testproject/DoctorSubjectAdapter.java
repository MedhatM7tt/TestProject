package com.example.heba.testproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by SM on 4/8/2018.
 */

public class DoctorSubjectAdapter extends ArrayAdapter<SubjectData>{

    private Button active1,active2;
    private Context mCtx;

    public DoctorSubjectAdapter(@NonNull Context context, @NonNull List<SubjectData> objects) {
        super(context, 0, objects);
        mCtx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView ==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.doc_list, parent, false);
        }


        final SubjectData currentSubject = getItem(position);
        final TextView subjectView = (TextView) listItemView.findViewById(R.id.subjectCodeDoc);
        active1 = (Button)listItemView.findViewById(R.id.active1);
        active2 = (Button)listItemView.findViewById(R.id.active2);

        subjectView.setText(currentSubject.getSubjectCode());

        setButtonText(currentSubject.getActiveEval1(),1);

        setButtonText(currentSubject.getActiveEval2(),2);

        active1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButton(currentSubject.getActiveEval1());
            }
        });
        active2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableButton(currentSubject.getActiveEval2());
            }
        });
        return listItemView;
    }
    private void enableButton(String mActive) {
        if(mActive.equals("1")){
            Toast.makeText(getContext(),"Is Already Active",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getContext(),"Active", Toast.LENGTH_LONG).show();


        }
    }

    private void setButtonText(String mActive,int n){
        if(n==1){
            if(mActive.equals("1"))
                active1.setText("DisActive Eval 1");
        }
        else if(n==2){
            if(mActive.equals("1"))
                active2.setText("DisActive Eval 2");
        }
    }
    private void setButtonText(String mActive,Button mBtn){
        if(mActive.equals("1"))
            mBtn.setText("DisActive Eval");
    }
}
