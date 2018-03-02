package com.example.heba.testproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Heba on 2/25/2018.
 */

public class SubjectsAdapter extends ArrayAdapter<String> {
    public SubjectsAdapter(@NonNull Context context, @NonNull List<String> objects) {
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
        final String currentSubject = getItem(position);
        TextView subjectView=(TextView) listItemView.findViewById(R.id.subjectCode);
        subjectView.setText(currentSubject);
        return listItemView;
    }
}
