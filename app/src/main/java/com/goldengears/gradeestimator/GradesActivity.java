 package com.goldengears.gradeestimator;

 import android.os.Bundle;
 import android.util.Log;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import com.goldengears.gradeestimator.adapters.GradesRecyclerAdapter;
 import com.goldengears.gradeestimator.models.Grade;

 import java.util.ArrayList;
 import java.util.Date;
 import java.util.Random;

 public class GradesActivity extends AppCompatActivity {

     private static final String TAG = "GradesActivity";

     //Variables
     private ArrayList<Grade> mGrades = new ArrayList<>();
     private GradesRecyclerAdapter mGradesRecyclerAdapter;

     //UI
     private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

//
//        Grade grade = new Grade("Test Grade", 0, true, "The end of time");
//        LinkedList<AbstractGrade> grades = new LinkedList<>();
//        grades.add(grade);
//
//        AssignmentType type = new AssignmentType("Testing", 50, 1, 0, grades);
//
//        Log.d(TAG, "onCreate: Test Grade created " + grade.toString());
//        Log.d(TAG, "onCreate: Test AssignmentType " + type.toString());

        Random rand = new Random();
        insertFakeGrade(rand.nextInt(1000));
    }

     private void insertFakeGrade(int numGrades) {

         Log.d(TAG, "insertFakeGrade: Number of grades to generate " + numGrades);
         for (int i = 0; i < numGrades; i++) {
             Date date = new Date();
             Grade grade = new Grade("Test Grade " + i, i, true, date.toString());
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

}
