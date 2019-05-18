package com.goldengears.gradeestimator.models;

import com.goldengears.gradeestimator.abstracts.AbstractGrade;
import com.goldengears.gradeestimator.abstracts.AbstractListicle;

import java.util.List;

public class AssignmentType extends AbstractListicle {
    public Integer dropMax;
    public Integer dropCount;

    public AssignmentType(String title, Integer score, Integer dropMax, Integer dropCount, List<AbstractGrade> grades) {
        this.title = title;
        this.score = score;
        this.dropMax = dropMax;
        this.dropCount = dropCount;
        this.grades = grades;
    }

    public Integer getDropMax() {
        return dropMax;
    }

    public void setDropMax(Integer dropMax) {
        this.dropMax = dropMax;
    }

    public Integer getDropCount() {
        return dropCount;
    }

    public void setDropCount(Integer dropCount) {
        this.dropCount = dropCount;
    }

    public List<AbstractGrade> getGrades() {
        return grades;
    }

    public void setGrades(List<AbstractGrade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "AssignmentType{" +
                "title='" + title + '\'' +
                ", score=" + score +
                ", dropMax=" + dropMax +
                ", dropCount=" + dropCount +
                ", grades=" + grades +
                '}';
    }
}
