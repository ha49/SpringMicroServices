package com.example.reservationservices;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReservationsModelAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>> {

  @Override
  public EntityModel<Reservation> toModel(Reservation reservation) {
    return new EntityModel<>(reservation,
            linkTo(methodOn(ReservationController.class).one(reservation.getId())).withSelfRel(),
            linkTo(methodOn(ReservationController.class).all()).withRel("reservations"));
  }

  @Override
  public CollectionModel<EntityModel<Reservation>> toCollectionModel(Iterable<? extends Reservation> entities) {
    var collection = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toModel)
            .collect(Collectors.toList());

    return new CollectionModel<>(collection,
            linkTo(methodOn(ReservationController.class).all()).withSelfRel());
  }
}
