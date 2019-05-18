 package com.goldengears.gradeestimator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.goldengears.gradeestimator.abstracts.AbstractGrade;
import com.goldengears.gradeestimator.models.AssignmentType;
import com.goldengears.gradeestimator.models.Grade;

import java.util.LinkedList;

 public class GradesActivity extends AppCompatActivity {

     private static final String TAG = "GradesActivity";
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Grade grade = new Grade("Test Grade", 0, true, "The end of time");

        LinkedList<AbstractGrade> grades = new LinkedList<>();
        grades.add(grade);

        AssignmentType type = new AssignmentType("Testing", 50, 1, 0, grades);

        Log.d(TAG, "onCreate: Test Grade created " + grade.toString());
        Log.d(TAG, "onCreate: Test AssignmentType " + type.toString());

    }

}
