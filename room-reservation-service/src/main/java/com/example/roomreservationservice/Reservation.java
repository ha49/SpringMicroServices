package com.example.roomreservationservice;

import java.util.Date;

public class Reservation {

  private long id;
  private long roomId;
  private long guestId;
  private Date date;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
  //  public RoomReservation(long roomId, long guestId, String floorName, String roomNumber, String firstName,
  //                         String lastName, String date) {
  //    this.roomId = roomId;
  //    this.guestId = guestId;
  //    this.floorName = floorName;
  //    this.roomNumber = roomNumber;
  //    this.firstName = firstName;
  //    this.lastName = lastName;
  //    this.date = date;
  //  }
  //
  //  public RoomReservation() {
  //  }



}