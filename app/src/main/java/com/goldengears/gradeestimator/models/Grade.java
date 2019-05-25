package com.goldengears.gradeestimator.models;

import com.goldengears.gradeestimator.abstracts.AbstractGrade;

/*
/ Grade struct to hold the individual assignments name, score, and due date. This also
/ hold if the current score used is a predicted grade or the actual grade based on a boolean
 */
public class Grade extends AbstractGrade {
    private Boolean predictive;
    private String date;

    public Grade(String title, Integer score, Boolean predictive, String date) {
        this.expanded = false;
        this.title = title;
        this.score = score;
        this.predictive = predictive;
        this.date = date;
    }

    public Boolean getPredictive() {
        return predictive;
    }

    public void setPredictive(Boolean predictive) {
        this.predictive = predictive;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "title='" + title + '\'' +
                ", score=" + score +
                ", predictive=" + predictive +
                ", date='" + date + '\'' +
                '}';
    }
}
