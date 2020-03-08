package com.example.guestservices;



import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GuestRepository extends JpaRepository<Guest, Long> {
  Optional<Guest> findById(Long id);
    Guest findByfirstName(String firstName);
}

