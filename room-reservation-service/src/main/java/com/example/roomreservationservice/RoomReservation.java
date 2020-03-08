package com.example.roomreservationservice;

import java.util.Date;

public class RoomReservation {

  private long roomId;
  private long guestId;
  private String floorName;
  private String roomNumber;
  private String firstName;
  private String lastName;
  private Date date;

  public long getRoomId() {
    return roomId;
  }

  public void setRoomId(long roomId) {
    this.roomId = roomId;
  }

  public long getGuestId() {
    return guestId;
  }

  public void setGuestId(long guestId) {
    this.guestId = guestId;
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
