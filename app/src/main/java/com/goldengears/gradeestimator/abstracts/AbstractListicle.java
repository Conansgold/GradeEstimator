package com.goldengears.gradeestimator.abstracts;

import java.util.List;

/*
  An extention of the Abstract Grade class to include a list of
 */
public abstract class AbstractListicle extends AbstractGrade {

    public List<AbstractGrade> grades;

    public List<AbstractGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<AbstractGrade> grades) {
        this.grades = grades;
    }
}
