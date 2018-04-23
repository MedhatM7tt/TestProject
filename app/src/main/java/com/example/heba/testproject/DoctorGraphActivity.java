package com.example.heba.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DoctorGraphActivity extends AppCompatActivity {
    BarChart barChart;
    String courseCode, evalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_graph);

        final ArrayList<String> yList,xList;

        barChart = (BarChart) findViewById(R.id.bargraph);
        ArrayList<BarEntry> barEntries= new ArrayList<>();


        yList = makeList(SharedPrefManager.getmInstance(this).getyValues());
        xList = makeList(SharedPrefManager.getmInstance(this).getxValues());
        Float yvalues[]=new Float[yList.size()];

        for (int i=0;i<yList.size();i++) {
            yvalues[i]=Float.parseFloat(yList.get(i));
        }


        for(int i=0;i<yvalues.length;i++){
            barEntries.add(new BarEntry(i,yvalues[i]));
        }

        courseCode=SharedPrefManager.getmInstance(this).getCourseCode();
        evalType =SharedPrefManager.getmInstance(this).getEvalType();
        BarDataSet barDataSet = new BarDataSet(barEntries,courseCode+" "+ evalType +" Exam Evaluation Graph");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis=barChart.getXAxis();

        xAxis.setValueFormatter(new MyFormat(xList));
        //xAxis.setGranularity(1);
        xAxis.setAxisMinimum(-.5f);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularityEnabled(true);

        YAxis yAxis=barChart.getAxisLeft();
        yAxis.setLabelCount(5);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);

        barChart.getAxisRight().setEnabled(false);
        barChart.setData(barData);

    }

    private ArrayList<String> makeList(String list){
        ArrayList<String> temp=new ArrayList<>();
        String [] array=list.split(",");
        for(int i=0;i<array.length;) {
            String  question =array[i++].trim();
            temp.add(question);
        }
        return temp;
    }
    private class MyFormat implements IAxisValueFormatter {
        ArrayList<String> xData;

        public MyFormat(ArrayList<String> xData) {
            this.xData = xData;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int intValue=(int)value;
            if (xData.size() > intValue && intValue >= 0)
                return xData.get((int)value);
            return "";
        }
    }

}
