package com.epam.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Semaphore semaphore;
    /**
     * Access flags for each of the rooms instances.
     */
    private boolean[] available = {
            true,
            true,
            true
    };

    /**
     * The  instances stored in the class.
     */
    private RoomList[] rooms = {
            new RoomList(),
            new RoomList(),
            new RoomList()
    };
    public Hotel() {
        for(int i =0; i<100; i++) {
            rooms[0].put(new Room(Room.RoomType.PRESIDENT));
            rooms[1].put(new Room((Room.RoomType.FOURSTAR)));
            rooms[2].put(new Room((Room.RoomType.NORMAL)));
        }
        semaphore = new Semaphore(3);
    }

    public synchronized int countRoom() {
        return rooms[0].countRoom() + rooms[1].countRoom() + rooms[2].countRoom();
    }

    public synchronized RoomList takeRoomList() {
        RoomList roomList = null;
        try {
            semaphore.acquire();
            if(available[0]) {
                roomList = rooms[0];
                available[0] = false;
            } else if(available[1]) {
                roomList = rooms[1];
                available[1] = false;
            } else if(available[2]) {
                roomList = rooms[2];
                available[2] = false;
            }
        }catch (InterruptedException ex) {
            System.out.println("InterruptedException is "+ex);
        }finally {
            semaphore.release();
        }
        return roomList;
    }

    public synchronized  void returnRoomList(RoomList roomList) {
        if(roomList == rooms[0]) {
            available[0] = true;
        } else if(roomList == rooms[1]) {
            available[1] = true;
        }  else if(roomList == rooms[2]) {
            available[2] = true;
        }
    }

}
