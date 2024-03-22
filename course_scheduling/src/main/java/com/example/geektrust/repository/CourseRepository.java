package com.example.geektrust.repository;

import com.example.geektrust.model.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseRepository {
    private  Map<String, Course> courseMap;
    public  CourseRepository(){
        this.courseMap = new HashMap<>();
    }
    public void addCourse(Course course){
        String id = course.getCourseOfferingId();
        if(courseMap.containsKey(id)){
            System.out.println("duplicate entry");
            return;
        }
        courseMap.put(id,course);
    }
    public Course getCourseById(String id){
        if(courseMap.containsKey(id))
            return courseMap.get(id);
        return null;
    }
    public Course getCourseByName(String name){
        Course currCourse = null;
        for(Course course:courseMap.values()){
            if(course.getTitle().equals(name)) {
                currCourse = course;
                break;
            }
        }
        return currCourse;
    }
}
