package com.example.guestservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/guests")
@Slf4j
public class GuestController {
  //private final GuestRepository repository;
  final GuestRepository repository;
  private final GuestsModelAssembler assembler;

  public GuestController(GuestRepository storage, GuestsModelAssembler guestsModelAssembler){
    this.repository = storage;
    this.assembler = guestsModelAssembler;
  }

  @GetMapping
  public CollectionModel<EntityModel<Guest>> all() {
    log.debug("All guests called");
    return assembler.toCollectionModel(repository.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<EntityModel<Guest>> one(@PathVariable long id) {
    return repository.findById(id)
            .map(assembler::toModel)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
    log.info("POST create Guest " + guest);
    var g = repository.save(guest);
    log.info("Saved to repository " + g);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(linkTo(GuestController.class).slash(g.getId()).toUri());
    //headers.add("Location", "/api/persons/" + p.getId());
    return new ResponseEntity<>(g, headers, HttpStatus.CREATED);


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
  ResponseEntity<Guest> replaceGuest(@RequestBody Guest newGuest, @PathVariable Long id) {
    return repository.findById(id)
            .map(guest -> {

              guest.setFirstName(newGuest.getFirstName());
              guest.setLastName(newGuest.getLastName());
              repository.save(guest);
              HttpHeaders headers = new HttpHeaders();
              headers.setLocation(linkTo(GuestController.class).slash(guest.getId()).toUri());
              return new ResponseEntity<>(guest, headers, HttpStatus.OK);
            })
            .orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PatchMapping("/{id}")
  ResponseEntity<Guest> modifyGuest(@RequestBody Guest newGuest, @PathVariable Long id) {
    return repository.findById(id)
            .map(guest -> {
              if (newGuest.getFirstName() != null)
                guest.setFirstName(newGuest.getFirstName());
              if (newGuest.getLastName() != null)
                guest.setLastName(newGuest.getLastName());

              repository.save(guest);
              HttpHeaders headers = new HttpHeaders();
              headers.setLocation(linkTo(GuestController.class).slash(guest.getId()).toUri());
              return new ResponseEntity<>(guest, headers, HttpStatus.OK);
            })
            .orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }



//  @GetMapping
//  public Iterable<Guest> getAllGuests(){
//    return this.repository.findAll();
//  }
//
//  @GetMapping("/{id}")
//  public Guest getGuest(@PathVariable("id")long id){
//    return this.repository.findById(id).get();
//  }
}
