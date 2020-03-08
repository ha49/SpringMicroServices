package com.example.roomreservationservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {
  private final RoomClient roomClient;
  private final GuestClient guestClient;
  private final ReservationClient reservationClient;
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


  public RoomReservationWebService(RoomClient roomClient, GuestClient guestClient, ReservationClient reservationClient){
    super();
    this.roomClient = roomClient;
    this.guestClient = guestClient;
    this.reservationClient = reservationClient;
  }



  @GetMapping
  public List<RoomReservation> getRoomReservations(@RequestParam(name = "date", required = false)String dateString){
    Date date = createDateFromDateString(dateString);
    List<Room> rooms = this.roomClient.getAllRooms();
    Map<Long, RoomReservation> roomReservations = new HashMap<>();
    rooms.forEach(room->{
      RoomReservation roomReservation = new RoomReservation();
      roomReservation.setRoomId(room.getId());
      roomReservation.setFloorName(room.getFloorName());
      roomReservation.setRoomNumber(room.getRoomNumber());
      roomReservations.put(room.getId(), roomReservation);
    });
    List<Reservation> reservations = this.reservationClient.getAllReservations(new Date(date.getTime()));
    reservations.forEach(reservation -> {
      RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
      roomReservation.setDate(date);
      Guest guest = this.guestClient.getGuest(reservation.getGuestId());
      roomReservation.setGuestId(guest.getId());
      roomReservation.setFirstName(guest.getFirstName());
      roomReservation.setLastName(guest.getLastName());
    });

    return new ArrayList<>(roomReservations.values());
  }




//  private List<Room> getAllRooms(){
//    ResponseEntity<List<Room>> roomResponse = this.restTemplate.exchange("http://ROOMSERVICES/api/rooms",
//            HttpMethod.GET
//            , null, new ParameterizedTypeReference<List<Room>>() {
//    });
//    return roomResponse.getBody();
//  }


  private Date createDateFromDateString(String dateString){
    Date date = null;
    if(null != dateString){
      try{
        date = DATE_FORMAT.parse(dateString);
      }catch(ParseException pe){
        date = new Date();
      }
    }else{
      date = new Date();
    }
    return date;
  }
//
//  private List<Guest> getAllGuests(){
//    ResponseEntity<List<Guest>> guestResponse = this.restTemplate.exchange("http://GUESTSERVICES/api/guests",
//            HttpMethod.GET
//            , null, new ParameterizedTypeReference<List<Guest>>() {
//    });
//    return guestResponse.getBody();
//  }
}