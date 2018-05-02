package com.example.heba.testproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfDiv;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class DoctorGraphActivity extends AppCompatActivity {
    BarChart barChart;

    String courseCode, evalType;
    private String dirpath;
    private static final int REQUEST_STORAGE=225;
    private static final int TAT_STORAGE=2;
    //ImageView savePdf;

    PermissionUri permissionUri;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseCode=SharedPrefManager.getmInstance(this).getCourseCode();
        evalType =SharedPrefManager.getmInstance(this).getEvalType();
        setTitle(courseCode+" "+evalType+" Graph");
        setContentView(R.layout.activity_doctor_graph);

        permissionUri = new PermissionUri(this);

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

        BarDataSet barDataSet = new BarDataSet(barEntries,courseCode+" "+ evalType +" Exam Evaluation Graph");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(.5f);

        XAxis xAxis=barChart.getXAxis();

        xAxis.setValueFormatter(new MyFormat(xList));
        //xAxis.setGranularity(1);
        xAxis.setAxisMinimum(-.5f);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelCount(xList.size());
        xAxis.setTextSize(7f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxis=barChart.getAxisLeft();
        yAxis.setLabelCount(5);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(5);

        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setData(barData);
        barDataSet.setValueTextSize(7);

    }

    private void saveGraph(){

        if(checkPermission(TAT_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                showPermissionExplanation(TAT_STORAGE);
            }
            else if(!permissionUri.checkPermission("storage")){
                requestPermission(TAT_STORAGE);
                permissionUri.updatePermission("storage");
            }
            else
            {
                MyToast.viewToast("Please Allow the storage permission",this);
                Intent intent= new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
        else {
            ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");
            progressDialog.show();
            dirpath=android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/EvaluationsGraphs";
            barChart.setDrawingCacheEnabled(true);
            Bitmap bitmap=Bitmap.createBitmap(barChart.getDrawingCache());

            barChart.setDrawingCacheEnabled(false);
            Log.v("BitMap",bitmap.toString());

            Document document = new Document();
            String fileName=courseCode+"_"+evalType;

            try {
                File dir = new File(dirpath);
                if(!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, fileName + ".pdf");

                FileOutputStream fout = new FileOutputStream(file);

                Log.v("FOUT",file.getPath());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                Image myImg = Image.getInstance(stream.toByteArray());

                PdfWriter.getInstance(document, fout);
                document.open();

                document.setPageSize(new com.itextpdf.text.Rectangle(myImg.getScaledWidth()+100, myImg.getScaledHeight()+100));
                document.newPage();

                document.add(myImg);
                MyToast.viewToast("Saved !",this);

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            document.close();
            viewPdf(fileName+".pdf","EvaluationsGraphs");
            progressDialog.dismiss();
        }
    }

    private void viewPdf(String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);
        Log.v("PATHS",Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);
        Uri path = Uri.fromFile(pdfFile);

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            MyToast.viewToast( "Can't read pdf file", this);
        }
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

    private int checkPermission(int permission){
        int status=PackageManager.PERMISSION_DENIED;
        switch (permission) {
            case TAT_STORAGE:
                status= ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
        }

        return status;
    }

    private void requestPermission(int permission){
        switch (permission) {
            case TAT_STORAGE:
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                break;
        }

    }

    private void showPermissionExplanation(final int permission){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        if(permission==TAT_STORAGE){
            builder.setMessage("This app need to write to your storage .. please allow!");
            builder.setTitle("Storage Permission needed..");
        }
        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermission(TAT_STORAGE);
            }
        });
        builder.setNegativeButton("Canel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.save_icon:
                saveGraph();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }
}
