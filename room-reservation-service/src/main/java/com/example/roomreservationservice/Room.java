package com.example.roomreservationservice;

public class Room {
  private long id;
  private String floorName;
  private String roomNumber;
  private String bedInfo;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
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
