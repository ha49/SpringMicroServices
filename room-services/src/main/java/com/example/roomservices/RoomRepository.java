package com.example.roomservices;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
  List<Room> findAll();
}
