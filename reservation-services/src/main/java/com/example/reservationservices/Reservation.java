package com.example.reservationservices;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long roomId;
    private long guestId;
    private Date date;

    public Reservation() {
    }

    public Reservation(long roomId, long guestId, Date date) {
        this.roomId = roomId;
        this.guestId = guestId;
        this.date = date;
    }

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
}
