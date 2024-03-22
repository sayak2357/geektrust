package com.example.geektrust.service;

import com.example.geektrust.model.Course;
import com.example.geektrust.repository.CourseRegistrationRepository;
import com.example.geektrust.repository.CourseRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ApplicationService {

    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final ValidationService validationService;
    public ApplicationService(){
        this.courseRepository = new CourseRepository();
        this.courseRegistrationRepository = new CourseRegistrationRepository();
        this.validationService = new ValidationService(courseRepository,courseRegistrationRepository);
    }
    public void run(Scanner sc){
        while (sc.hasNextLine()) {
            //System.out.println(sc.nextLine());
            String[] ops = sc.nextLine().split(" ");
            String op = ops[0];
            if(op.equals("ADD-COURSE-OFFERING")){
                Integer commandLength = ops.length;
                if(commandLength!=6){
                    System.out.println("INPUT_DATA_ERROR");
                }
                else{
                    String name = ops[1];
                    String instructor = ops[2];
                    String date = ops[3];
                    Integer minCount = Integer.parseInt(ops[4]);
                    Integer maxCount = Integer.parseInt(ops[5]);
                    Course course = new Course(name,instructor,date,minCount,maxCount);
                    courseRepository.addCourse(course);
                    System.out.println(course.getCourseOfferingId());
                }
            }
            else if(op.equals("REGISTER")){
                Integer commandLength = ops.length;
                if(commandLength!=3){
                    System.out.println("INPUT_DATA_ERROR");
                }
                else{
                    String email = ops[1];
                    String courseId = ops[2];
                    if(validationService.canNewRegistration(courseId)){
                        courseRegistrationRepository.addUserToCourse(courseId,email);
                        Course course = courseRepository.getCourseById(courseId);
                        String name = course.getTitle();
                        String empName = findName(email);
                        String courseRegId = "REG_COURSE-"+empName+"-"+name;
                        System.out.println(courseRegId+" "+"ACCEPTED");
                    }
                    else{
                        System.out.println("COURSE_FULL_ERROR");
                    }
                }
            }
        }
        sc.close(); // closes the scanner
    }
    private String findName(String email){
        String[] nameAndDomain = email.split("@");
        return nameAndDomain[0];
    }
}
