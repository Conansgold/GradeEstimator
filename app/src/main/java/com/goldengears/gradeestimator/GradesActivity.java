 package com.goldengears.gradeestimator;

 import android.app.DatePickerDialog;
 import android.app.TimePickerDialog;
 import android.content.Context;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.widget.Button;
 import android.widget.CheckBox;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.TimePicker;

 import androidx.appcompat.app.AlertDialog;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.goldengears.gradeestimator.adapters.GradesRecyclerAdapter;
 import com.goldengears.gradeestimator.models.Grade;
 import com.google.android.material.floatingactionbutton.FloatingActionButton;
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;

 import java.io.BufferedReader;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.sql.Time;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Random;

 public class GradesActivity extends AppCompatActivity {

     private static final String TAG = "GradesActivity";
     private static Context mGradesActivityContext;

     //Variables
     private static ArrayList<Grade> mGrades = new ArrayList<>();
     public GradesRecyclerAdapter mGradesRecyclerAdapter;


     //UI
     private RecyclerView mRecyclerView;

     public static Context getmGradesActivityContext() {
         return mGradesActivityContext;
     }

     public static void writeToFile(String data) {
         Context context = getmGradesActivityContext();
         try {
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
             outputStreamWriter.write(data);
             outputStreamWriter.close();
         } catch (IOException e) {
             Log.e("Exception", "File write failed: " + e.toString());
         }
     }

     public static ArrayList<Grade> getGrades() {
         return mGrades;
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
             String data = gson.toJson(mGrades);
             writeToFile(data);
         } else {
             Gson data = new Gson();
             mGrades.addAll(data.fromJson(fileInfo, new TypeToken<ArrayList<Grade>>() {
             }.getType()));

             Log.d(TAG, "onCreate: " + data);
             Log.d(TAG, "onCreate: " + mGrades);
             Random rand = new Random();
         }

         FloatingActionButton addEntry = findViewById(R.id.actionButton);
         addEntry.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 buildGradeDialog();
             }
         });
    }

     private void buildGradeDialog() {

         AlertDialog.Builder mBuilder = new AlertDialog.Builder(getmGradesActivityContext());
         View view = getLayoutInflater().inflate(R.layout.add_entry_dialog, null);

         SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy");
         SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
         Date date = new Date();

         mBuilder.setView(view);
         AlertDialog dialog = mBuilder.create();
         dialog.show();

         final EditText editTitle = view.findViewById(R.id.grade_title_edit);
         final EditText editScore = view.findViewById(R.id.edit_score);
         final CheckBox finalGrade = view.findViewById(R.id.final_grade);
         final TextView dueDate = view.findViewById(R.id.edit_date);
         final TextView dueTime = view.findViewById(R.id.edit_time);
         final Button saveGrade = view.findViewById(R.id.save_grade);


         dueDate.setText(dateFormat.format(date));
         dueTime.setText(timeFormat.format(date));


         Calendar cal = Calendar.getInstance();
         DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
             @Override
             public void onDateSet(DatePicker view, int year, int monthOfYear,
                                   int dayOfMonth) {
                 cal.set(Calendar.YEAR, year);
                 cal.set(Calendar.MONTH, monthOfYear);
                 cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                 dueDate.setText(dateFormat.format(cal.getTime()));
             }
         };

         dueDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 DatePickerDialog dialog = new DatePickerDialog(GradesActivity.getmGradesActivityContext(),
                         datePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                         cal.get(Calendar.DAY_OF_MONTH));
                 dialog.show();
             }
         });


         TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
             @Override
             public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                 cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                 cal.set(Calendar.MINUTE, minute);
                 dueTime.setText(timeFormat.format(cal.getTime()));
             }
         };

         dueTime.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 TimePickerDialog timeDialog = new TimePickerDialog(GradesActivity.getmGradesActivityContext(),
                         timePickerListener, 01, 02, false);
                 timeDialog.show();
             }
         });

         saveGrade.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Integer score = Integer.valueOf(editScore.getText().toString());
                 Grade grade = new Grade(editTitle.getText().toString(), score,
                         finalGrade.isChecked(), dueDate.getText().toString(), dueTime.getText().toString());
                 mGrades.add(grade);
                 mGradesRecyclerAdapter.notifyDataSetChanged();
                 dialog.cancel();

                 Gson gson = new Gson();
                 String data = gson.toJson(mGrades);
                 writeToFile(data);
             }
         });
     }
 }
