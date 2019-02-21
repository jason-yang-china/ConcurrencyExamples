package com.epam.concurrent.semaphore;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Room {

    public static enum RoomType {
        PRESIDENT,
        FOURSTAR,
        NORMAL
    }

    private RoomType roomType;

    public Room(RoomType type) {
        this.roomType = type;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public String toString() {
        switch (roomType) {
            case NORMAL:
                return "Normal Room";
            case FOURSTAR:
                return "Fourstar Room";
            case PRESIDENT:
                return "President Room";
            default:
                return "";
        }
    }
}
