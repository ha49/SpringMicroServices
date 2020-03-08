package com.example.roomservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class SetupDatabase {

  @Bean
  CommandLineRunner initDatabase(RoomRepository repository) {
    return args -> {
      if (repository.count() == 0) {
        //New empty database, add some persons
        //        repository.save(new Guest("aa", "bb"));
        log.info("Room 1 added to database " + repository.save(new Room("1st floor", "100", "One single bed")));
        log.info("Room 2 added to database " + repository.save(new Room("1st floor", "101", "One single bed")));
        log.info("Room 3 added to database " + repository.save(new Room("1st floor", "102", "One single bed")));
        log.info("Room 4 added to database " + repository.save(new Room("2nd floor", "200", "One double bed")));
        log.info("Room 5 added to database " + repository.save(new Room("2nd floor", "201", "One double bed")));
        log.info("Room 6 added to database " + repository.save(new Room("2nd floor", "202", "One double bed")));
        log.info("Room 7 added to database " + repository.save(new Room("2nd floor", "203", "One double bed")));
        log.info("Room 8 added to database " + repository.save(new Room("2nd floor", "204", "One double bed")));
        log.info("Room 9 added to database " + repository.save(new Room("3rd floor", "300", "Two single beds")));
        log.info("Room 10 added to database " + repository.save(new Room("3rd floor", "301", "Two single beds")));
        log.info("Room 11 added to database " + repository.save(new Room("3rd floor", "302", "One double king bed")));
        log.info("Room 12 added to database " + repository.save(new Room("3rd floor", "303", "One double king bed")));


      }
    };
  }

  @Bean
  @LoadBalanced
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }


}