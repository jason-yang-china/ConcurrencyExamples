package com.epam.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
   private List<Room> rooms = new ArrayList<Room>();

   public Room takeRoom() {
       if(!rooms.isEmpty()) {
           return rooms.remove(0);
       } else {
           return null;
       }

   }

   public void put(Room room) {
       rooms.add(room);
   }

   public int countRoom() {
       return rooms.size();
   }

    /**
     * toString method
     */
    public String toString() {
        int president = 0; //PRESIDENT
        int fourstar = 0;
        int normal = 0;

        for (Room r : rooms) {
            switch (r.getRoomType()) {
                case PRESIDENT:
                    president++;
                    break;
                case FOURSTAR:
                    fourstar++;
                    break;
                case NORMAL:
                    normal++;
                    break;
                default:
            }
        }

        return president + " PRESIDENT Rooms, " + fourstar + " Fourstar Rooms, and " + normal + "  Normal Rooms";
    }

}
