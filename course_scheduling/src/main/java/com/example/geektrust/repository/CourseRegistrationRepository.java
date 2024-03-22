package com.example.geektrust.repository;

import com.example.geektrust.model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRegistrationRepository {
    private Map<String, List<String>> courseRegitrationMap;
    public CourseRegistrationRepository(){
        this.courseRegitrationMap = new HashMap<>();
    }
    public void addUserToCourse(String courseOfferingId, String newUserEmail){

        if(courseRegitrationMap.containsKey(courseOfferingId)){
            List<String> users = courseRegitrationMap.get(courseOfferingId);
            users.add(newUserEmail);
            courseRegitrationMap.put(courseOfferingId,users);
        }
        else {
            List<String> users = new ArrayList<>();
            users.add(newUserEmail);
            courseRegitrationMap.put(courseOfferingId,users);
        }
    }
    public Integer getRegisteredUsersSize(String courseOfferingId){
        Integer size = 0;
        if(courseRegitrationMap.containsKey(courseOfferingId)){
            List<String> users = courseRegitrationMap.get(courseOfferingId);
            size = users.size();
        }
        return size;
    }
    public List<String> getRegisteredUsers(String courseOfferingId){
        if(courseRegitrationMap.containsKey(courseOfferingId)){
            return courseRegitrationMap.get(courseOfferingId);
        }
        return null;
    }
}
