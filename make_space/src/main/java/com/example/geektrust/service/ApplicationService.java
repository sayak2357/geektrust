package com.example.geektrust.service;

import com.example.geektrust.repository.Rooms;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class ApplicationService {
    private BookingService bookingService;
    private TimestampSyntaxChecker timestampSyntaxChecker;
    public ApplicationService(){
        this.bookingService = new BookingService();
        this.timestampSyntaxChecker = new TimestampSyntaxChecker();
    }

    public void run(Scanner sc){
        while(sc.hasNextLine()){
            String ip = sc.nextLine();
            String[] tokens = ip.split(" ");
            //System.out.println(ip);
            if(tokens[0].equals("VACANCY")){
                List<String> vacancies = bookingService.vacancies(tokens[1],tokens[2]);
                if(vacancies.size()==0) {
                    System.out.println("NO_VACANT_ROOM");
                }
                else {
                    for (String room : vacancies)
                        System.out.printf(room + " ");
                    System.out.println();
                }
            }
            else if(tokens[0].equals("BOOK")){
                String startTime = tokens[1];
                String endTime = tokens[2];
                Integer persons = Integer.parseInt(tokens[3]);

                if(!timestampSyntaxChecker.isValidTimestamps(startTime,endTime))
                    System.out.println("INCORRECT_INPUT");
                else {
                    bookingService.allotRoom(startTime, endTime, persons);
                }
            }
        }
    }

}
