package com.example.heba.testproject;

import android.content.Context;
import android.content.Intent;
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
 * Created by Heba on 2/25/2018.
 */

public class SubjectsAdapter extends ArrayAdapter<SubjectData> {
    public SubjectsAdapter(@NonNull Context context, @NonNull List<SubjectData> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.mylist, parent, false);
        }
        final SubjectData currentSubject = getItem(position);
        final TextView subjectView=(TextView) listItemView.findViewById(R.id.subjectCode);
        Button eval1 = (Button) listItemView.findViewById(R.id.eval1);
        Button eval2 = (Button) listItemView.findViewById(R.id.eval2);
        subjectView.setText(currentSubject.getSubjectCode());
        eval1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButton(currentSubject.getDoneEval1(),currentSubject.getActiveEval1());
            }
        });
        eval2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableButton(currentSubject.getDoneEval2(),currentSubject.getActiveEval2());
            }
        });
        return listItemView;
    }
    private void enableButton(String mDoneEval,String mActiveEval){
        if(mDoneEval.equals("1"))
        {
            Toast.makeText(getContext(), "The Evaluation Solved", Toast.LENGTH_SHORT).show();
        }
        else{
            if(mActiveEval.equals("1"))
            {
                Toast.makeText(getContext(), "Wait For Get Data ", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(),eval_questionsActivity.class));
            }
            else{
                Toast.makeText(getContext(), "The Evaluation Not Active Now ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
