package com.goldengears.gradeestimator.models;

import com.goldengears.gradeestimator.abstracts.AbstractGrade;
import com.goldengears.gradeestimator.abstracts.AbstractListicle;

import java.util.List;

public class Course extends AbstractListicle {
    public Integer goalGrade;

    public Course(String title, Integer score, Integer goalGrade, List<AbstractGrade> grades) {
        this.title = title;
        this.score = score;
        this.goalGrade = goalGrade;
        this.grades = grades;
    }

    public Integer getGoalGrade() {
        return goalGrade;
    }

    public void setGoalGrade(Integer goalGrade) {
        this.goalGrade = goalGrade;
    }

    @Override
    public String toString() {
        return "Course{" +
                "goalGrade=" + goalGrade +
                ", grades=" + grades +
                ", title='" + title + '\'' +
                ", score=" + score +
                '}';
    }
}
