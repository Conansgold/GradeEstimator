package com.goldengears.gradeestimator.abstracts;

/*
  Abstract Grade wrapper to be used in all other sub classes as they all use a
  title and a score.
 */
public abstract class AbstractGrade {
    public String title;
    public Integer score;
    public Boolean expanded;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
