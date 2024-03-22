package com.example.geektrust.service;

import com.example.geektrust.model.Course;
import com.example.geektrust.repository.CourseRegistrationRepository;
import com.example.geektrust.repository.CourseRepository;

public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    public CourseService(CourseRepository courseRepository, CourseRegistrationRepository courseRegistrationRepository){
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
    public void cancelReg(String courseRegId){
        String courseName = getCourseName(courseRegId);
        String uname = getCourseUserName(courseRegId);
        Course course = courseRepository.getCourseByName(courseName);
        if(course.isAllotted()){
            System.out.println(courseRegId+" "+"CANCEL_REJECTED");
        }
        else{

            courseRegistrationRepository.removeUser(course.getCourseOfferingId(),uname);
            System.out.println(courseRegId+" "+"CANCEL_ACCEPTED");
        }
    }
    private String getCourseName(String courseRegId){
        String[] vals = courseRegId.split("-");
        return vals[vals.length-1];
    }
    private String getCourseUserName(String courseRegId){
        String[] vals = courseRegId.split("-");
        return vals[vals.length-2];
    }
}
