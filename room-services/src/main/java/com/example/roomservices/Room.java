package com.example.roomservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String FloorName;
    private String roomNumber;
    private String bedInfo;

    public Room() {
    }

    public Room(String FloorName, String roomNumber, String bedInfo) {
        this.FloorName = FloorName;
        this.roomNumber = roomNumber;
        this.bedInfo = bedInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFloorName() {
        return FloorName;
    }

    public void setFloorName(String roomName) {
        this.FloorName = roomName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }
}