package com.example.geektrust.service;

import com.example.geektrust.repository.Rooms;

import java.util.Scanner;

public class ApplicationService {
    private BookingService bookingService;
    private Rooms rooms;
    public ApplicationService(){
        this.bookingService = new BookingService();
        this.rooms = Initializer.getInitializedRooms();
        initializeApplicationService();
    }
    private void initializeApplicationService(){
        bookingService.addRooms(this.rooms.getAllRooms());
    }
    public void run(Scanner sc){
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }
}
