package com.example.roomservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/rooms")
@Slf4j
public class RoomController {
//    private final RoomRepository repository;
    final RoomRepository repository;
    private final RoomModelAssembler assembler;

    public RoomController(RoomRepository storage, RoomModelAssembler roomModelAssembler){
        this.repository = storage;
        this.assembler=roomModelAssembler;
    }


    @GetMapping
    public CollectionModel<EntityModel<Room>> all() {
        log.debug("All rooms called");
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntityModel<Room>> one(@PathVariable long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        log.info("POST create Room " + room);
        var r = repository.save(room);
        log.info("Saved to repository " + r);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(RoomController.class).slash(r.getId()).toUri());
        //headers.add("Location", "/api/persons/" + p.getId());
        return new ResponseEntity<>(r, headers, HttpStatus.CREATED);


    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        if (repository.existsById(id)) {
            //log.info("Product deleted");
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    ResponseEntity<Room> replaceRoom(@RequestBody Room newRoom, @PathVariable Long id) {
        return repository.findById(id)
                .map(room -> {
                    room.setFloorName(newRoom.getFloorName());
                    room.setRoomNumber(newRoom.getRoomNumber());
                    room.setBedInfo(newRoom.getBedInfo());
                    repository.save(room);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(linkTo(RoomController.class).slash(room.getId()).toUri());
                    return new ResponseEntity<>(room, headers, HttpStatus.OK);
                })
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}")
    ResponseEntity<Room> modifyRoom(@RequestBody Room newRoom, @PathVariable Long id) {
        return repository.findById(id)
                .map(room -> {
                    if (newRoom.getFloorName() != null)
                        room.setFloorName(newRoom.getFloorName());
                    if (newRoom.getRoomNumber() != null)
                        room.setRoomNumber(newRoom.getRoomNumber());
                    if (newRoom.getBedInfo() != null)
                        room.setBedInfo(newRoom.getBedInfo());
                    repository.save(room);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(linkTo(RoomController.class).slash(room.getId()).toUri());
                    return new ResponseEntity<>(room, headers, HttpStatus.OK);
                })
                .orElseGet(() ->
                        new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    //    @GetMapping
//    public Iterable<Room> getAllRooms(){
//        return this.repository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Room getRoom(@PathVariable("id")long id){
//        return this.repository.findById(id).get();
//    }
}
