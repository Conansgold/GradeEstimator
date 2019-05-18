package com.goldengears.gradeestimator.models;

import com.goldengears.gradeestimator.abstracts.AbstractGrade;

/*
/ Grade struct to hold the individual assignments name, score, and due date. This also
/ hold if the current score used is a predicted grade or the actual grade based on a boolean
 */
public class Grade extends AbstractGrade {
    private Boolean predictive;
    private String timestamp;

    public Grade(String title, Integer score, Boolean predictive, String timestamp) {
        this.title = title;
        this.score = score;
        this.predictive = predictive;
        this.timestamp = timestamp;
    }

    public Boolean getPredictive() {
        return predictive;
    }

    public void setPredictive(Boolean predictive) {
        this.predictive = predictive;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "title='" + title + '\'' +
                ", score=" + score +
                ", predictive=" + predictive +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
