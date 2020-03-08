package com.example.roomservices;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class RoomModelAssembler implements RepresentationModelAssembler<Room, EntityModel<Room>> {

  @Override
  public EntityModel<Room> toModel(Room room) {
    return new EntityModel<>(room,
            linkTo(methodOn(RoomController.class).one(room.getId())).withSelfRel(),
            linkTo(methodOn(RoomController.class).all()).withRel("rooms"));
  }

  @Override
  public CollectionModel<EntityModel<Room>> toCollectionModel(Iterable<? extends Room> entities) {
    var collection = StreamSupport.stream(entities.spliterator(), false)
            .map(this::toModel)
            .collect(Collectors.toList());

    return new CollectionModel<>(collection,
            linkTo(methodOn(RoomController.class).all()).withSelfRel());
  }
}
