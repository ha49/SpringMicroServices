package com.example.reservationservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/reservations")
@Slf4j
public class ReservationController {
//    private ReservationRepository repository;
    final ReservationRepository repository;
    private final ReservationsModelAssembler assembler;


    public ReservationController(ReservationRepository storage, ReservationsModelAssembler reservationsModelAssembler){
         this.repository = storage;
         this.assembler = reservationsModelAssembler;
    }
    @GetMapping
    public CollectionModel<EntityModel<Reservation>> all() {
        log.debug("All reservations called");
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Reservation>> one(@PathVariable long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        log.info("POST create Reservation " + reservation);
        var r = repository.save(reservation);
        log.info("Saved to repository " + r);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(ReservationController.class).slash(r.getId()).toUri());
        //headers.add("Location", "/api/reservations/" + r.getId());
        return new ResponseEntity<>(r, headers, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteGuest(@PathVariable Long id) {
        if (repository.existsById(id)) {
            //log.info("Product deleted");
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<Reservation> replaceReservation(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return repository.findById(id)
                .map(reservation -> {
                    reservation.setRoomId(newReservation.getRoomId());
                    reservation.setGuestId(newReservation.getGuestId());
                    reservation.setDate(newReservation.getDate());
                    repository.save(reservation);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(linkTo(ReservationController.class).slash(reservation.getId()).toUri());
                    return new ResponseEntity<>(reservation, headers, HttpStatus.OK);
                })
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    ResponseEntity<Reservation> modifyReservation(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return repository.findById(id)
                .map(reservation-> {
                    if (newReservation.getRoomId()!=0 )
                        reservation.setRoomId(newReservation.getRoomId());
                    if (newReservation.getGuestId() != 0)
                        reservation.setGuestId(newReservation.getGuestId());
                    if (newReservation.getDate() != null)
                        reservation.setDate(newReservation.getDate());

                    repository.save(reservation);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(linkTo(ReservationController.class).slash(reservation.getId()).toUri());
                    return new ResponseEntity<>(reservation, headers, HttpStatus.OK);
                })
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


//    @GetMapping
//    public Iterable<Reservation> getAllReservations(){
//        return this.repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Reservation getReservation(@PathVariable("id") long id){
//        return this.repository.findById(id).get();
    }

