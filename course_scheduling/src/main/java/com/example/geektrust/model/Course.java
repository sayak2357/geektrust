package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String title,instructor,date;
    private Integer minEmployeeCount,maxEmployeeCount;
    private boolean isAllotted;

    private final String courseOfferingId;
    public Course(String courseName,String instructor, String date, Integer minEmployeeCount, Integer maxEmployeeCount){
        this.title = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minEmployeeCount = minEmployeeCount;
        this.maxEmployeeCount = maxEmployeeCount;
        this.courseOfferingId = "OFFERING"+"-"+courseName+"-"+instructor;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMinEmployeeCount() {
        return minEmployeeCount;
    }

    public void setMinEmployeeCount(Integer minEmployeeCount) {
        this.minEmployeeCount = minEmployeeCount;
    }

    public Integer getMaxEmployeeCount() {
        return maxEmployeeCount;
    }

    public void setMaxEmployeeCount(Integer maxEmployeeCount) {
        this.maxEmployeeCount = maxEmployeeCount;
    }

    public boolean isAllotted() {
        return isAllotted;
    }

    public String getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setAllotted(boolean allotted) {
        isAllotted = allotted;
    }
}
