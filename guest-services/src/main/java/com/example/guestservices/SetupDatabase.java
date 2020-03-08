package com.example.guestservices;

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
  CommandLineRunner initDatabase(GuestRepository repository) {
    return args -> {
      if (repository.count() == 0) {
        //New empty database, add some persons
        //        repository.save(new Guest("aa", "bb"));
        log.info("Guest 1 added to database" + repository.save(new Guest("Jack", "Nicholson")));
        log.info("Guest 2 added to database" + repository.save(new Guest("Tom", "Hanks")));
        log.info("Guest 3 added to database" + repository.save(new Guest("Robert", "De Niro")));
        log.info("Guest 4 added to database" + repository.save(new Guest("Dustin", "Hoffman")));
        log.info("Guest 5 added to database" + repository.save(new Guest("Denzel", "Washington")));
        log.info("Guest 6 added to database" + repository.save(new Guest("Morgan", "Freeman")));
        log.info("Guest 7 added to database" + repository.save(new Guest("Jodie", "Foster")));
        log.info("Guest 8 added to database" + repository.save(new Guest("Julia", "Roberts")));
        log.info("Guest 9 added to database" + repository.save(new Guest("Natalie", "Portman")));
        log.info("Guest 10 added to database" + repository.save(new Guest("Nicole", "Kidman")));
        log.info("Guest 11 added to database" + repository.save(new Guest("Julianne", "Moore")));
        log.info("Guest 12 added to database" + repository.save(new Guest("Scarlett", "Johansson")));
        //      }
      }
    };
  }

  @Bean
  @LoadBalanced
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }


}