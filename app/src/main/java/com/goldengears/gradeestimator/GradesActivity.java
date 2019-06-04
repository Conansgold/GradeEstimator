 package com.goldengears.gradeestimator;

 import android.content.Context;
 import android.os.Bundle;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.goldengears.gradeestimator.adapters.GradesRecyclerAdapter;
 import com.goldengears.gradeestimator.models.Grade;
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;

 import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.sql.Time;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.Random;

 public class GradesActivity extends AppCompatActivity {

     private static final String TAG = "GradesActivity";
     private static Context mGradesActivityContext;

     //Variables
     private ArrayList<Grade> mGrades = new ArrayList<>();
     private GradesRecyclerAdapter mGradesRecyclerAdapter;


     //UI
     private RecyclerView mRecyclerView;


     public static Context getmGradesActivityContext() {
         return mGradesActivityContext;
     }


     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

        mGradesActivityContext = this;

         //Get the info from the file and if it is empty create test data
         String fileInfo = readFromFile(getmGradesActivityContext());
         if (fileInfo == null || fileInfo.length() == 0 || fileInfo.trim().length() == 0) {
             Random rand = new Random();
             insertFakeGrade(rand.nextInt(10));

             Gson gson = new Gson();
             String arrayData = gson.toJson(mGrades);
             writeToFile(arrayData, getmGradesActivityContext());
             Log.d(TAG, "onCreate: written to file " + arrayData);
         } else {
             Gson data = new Gson();
             mGrades.addAll(data.fromJson(fileInfo, new TypeToken<ArrayList<Grade>>() {
             }.getType()));

             Log.d(TAG, "onCreate: " + data);
             Log.d(TAG, "onCreate: " + mGrades);
             Random rand = new Random();
         }


    }

     private void insertFakeGrade(int numGrades) {

         Log.d(TAG, "insertFakeGrade: Number of grades to generate " + numGrades);
         for (int i = 0; i < numGrades; i++) {
             Date date = new Date();
             Time time = new Time(1, 1, 1);
             Grade grade = new Grade("Test Grade " + i, i, true, date.toString(),
                     time.toString());
             mGrades.add(grade);
         }
         mGradesRecyclerAdapter.notifyDataSetChanged();
     }

     private void initRecyclerView() {
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
         mRecyclerView.setLayoutManager(linearLayoutManager);
         mGradesRecyclerAdapter = new GradesRecyclerAdapter(mGrades);
         mRecyclerView.setAdapter(mGradesRecyclerAdapter);
     }

     private String readFromFile(Context context) {

         String ret = "";
         Log.d(TAG, "readFromFile: reading from file");

         try {
             InputStream inputStream = context.openFileInput("config.txt");

             if (inputStream != null) {
                 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                 String receiveString = "";
                 StringBuilder stringBuilder = new StringBuilder();

                 while ((receiveString = bufferedReader.readLine()) != null) {
                     stringBuilder.append(receiveString);
                 }

                 inputStream.close();
                 ret = stringBuilder.toString();
             }
         } catch (FileNotFoundException e) {
             Log.e("login activity", "File not found: " + e.toString());
         } catch (IOException e) {
             Log.e("login activity", "Can not read file: " + e.toString());
         }

         Log.d(TAG, "readFromFile: return is empty? " + ret.isEmpty());
         Log.d(TAG, "readFromFile: " + ret);
         return ret;
     }

     private void writeToFile(String data, Context context) {
         try {
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
             outputStreamWriter.write(data);
             outputStreamWriter.close();
         } catch (IOException e) {
             Log.e("Exception", "File write failed: " + e.toString());
         }
     }

 }
