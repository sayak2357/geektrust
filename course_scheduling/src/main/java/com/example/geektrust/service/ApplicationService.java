package com.example.geektrust.service;

import com.example.geektrust.model.Course;
import com.example.geektrust.repository.CourseRegistrationRepository;
import com.example.geektrust.repository.CourseRepository;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ApplicationService {

    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final CourseService courseService;
    public ApplicationService(){
        this.courseRepository = new CourseRepository();
        this.courseRegistrationRepository = new CourseRegistrationRepository();
        this.courseService = new CourseService(courseRepository,courseRegistrationRepository);
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
                    if(courseService.canNewRegistration(courseId)){
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
            else if(op.equals("ALLOT")){
                Integer commandLength = ops.length;
                if(commandLength<2){
                    System.out.println("INPUT_DATA_ERROR");
                }
                else{
                    String courseId = ops[1];
                    Course course = courseRepository.getCourseById(courseId);
                    List<String> registeredUsers = courseRegistrationRepository.getRegisteredUsers(courseId);
                    Collections.sort(registeredUsers);
                    for(String userEmail:registeredUsers){
                        String empName = findName(userEmail);
                        String courseRegId = "REG_COURSE-"+empName+"-"+course.getTitle();
                        String instructor = course.getInstructor();
                        String date = course.getDate();
                        String status = courseService.courseStatus(courseId);
                        String courseName = course.getTitle();
                        System.out.println(courseRegId+" "+userEmail+" "+courseId+" "+courseName+" "+instructor+" "+date+" "+status);
                    }

                }
            }
            else if(op.equals("CANCEL")){
                Integer commandLength = ops.length;
                if(commandLength<2){
                    System.out.println("INPUT_DATA_ERROR");
                }
                else{
                    courseService.cancelReg(ops[1]);
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
