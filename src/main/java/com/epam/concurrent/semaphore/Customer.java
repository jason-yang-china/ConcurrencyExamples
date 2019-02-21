package com.epam.concurrent.semaphore;


import java.util.logging.Logger;

public class Customer extends Thread {

    //private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
    private final Hotel hotel;
    private final String name;
    private final RoomList roomList;


    public Customer(String name, Hotel hotel) {
        this.name = name;
        this.hotel = hotel;
        roomList = new RoomList();
    }

    public void run() {
        while (hotel.countRoom() > 0) {
            RoomList list = hotel.takeRoomList();
            Room room;

            if(list !=null ) {
                room = list.takeRoom();
                if(room != null) {
                    System.out.println(name + " took an " + room);
                    this.roomList.put(room);
                    hotel.returnRoomList(list);
                }
            }
        }

        System.out.println(name+" took "+roomList);
    }
}
