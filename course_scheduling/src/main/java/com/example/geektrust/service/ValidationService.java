package com.example.geektrust.service;

import com.example.geektrust.model.Course;
import com.example.geektrust.repository.CourseRegistrationRepository;
import com.example.geektrust.repository.CourseRepository;

public class ValidationService {
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    public ValidationService(CourseRepository courseRepository,CourseRegistrationRepository courseRegistrationRepository){
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    public boolean canNewRegistration(String courseOfferingId){
        Course course = courseRepository.getCourseById(courseOfferingId);
        Integer upperLimit = course.getMaxEmployeeCount();
        Integer userCount = courseRegistrationRepository.getRegisteredUsersSize(courseOfferingId);
        return userCount<upperLimit;
    }
    public String courseStatus(String courseOfferingId){
        String status = "CANCELLED";
        Course course = courseRepository.getCourseById(courseOfferingId);
        Integer upperLimit = course.getMaxEmployeeCount();
        Integer lowerLimit = course.getMinEmployeeCount();
        Integer userCount = courseRegistrationRepository.getRegisteredUsersSize(courseOfferingId);
        if(userCount>=lowerLimit && userCount<=upperLimit){
            status = "CONFIRMED";
        }
        if(status.equals("CONFIRMED"))
            course.setAllotted(true);
        return status;
    }
}
