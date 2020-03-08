package com.example.guestservices;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
  Optional<Guest> findById(Long id);
  Guest findByfirstName(String firstName);
}
