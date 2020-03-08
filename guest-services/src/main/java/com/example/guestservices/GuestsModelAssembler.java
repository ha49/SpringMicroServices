package com.example.guestservices;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GuestsModelAssembler implements RepresentationModelAssembler<Guest, EntityModel<Guest>> {

  @Override
  public EntityModel<Guest> toModel(Guest guest) {
    return new EntityModel<>(guest,
            linkTo(methodOn(GuestController.class).one(guest.getId())).withSelfRel(),
            linkTo(methodOn(GuestController.class).all()).withRel("guests"));
  }

  @Override
  public CollectionModel<EntityModel<Guest>> toCollectionModel(Iterable<? extends Guest> entities) {
    var collection = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toModel)
            .collect(Collectors.toList());

    return new CollectionModel<>(collection,
            linkTo(methodOn(GuestController.class).all()).withSelfRel());
  }
}
