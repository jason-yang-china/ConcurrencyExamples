package com.epam.concurrent.semaphore;

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
}
