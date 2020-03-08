package com.example.reservationservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
@Slf4j
public class SetupDatabase {

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  @Bean
  CommandLineRunner initDatabase(ReservationRepository repository) {
    return args -> {
      if (repository.count() == 0) {

        log.info("Reservation added to database " +
                repository.save(new Reservation(1, 1,dateFromString("2020-03-23"))));
        log.info("Reservation added to database " +
                repository.save(new Reservation(2, 2,dateFromString("2020-04-04"))));

      }
    };
  }

  @Bean
  @LoadBalanced
  RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  private Date dateFromString(String dateString){
    Date date = null;
    if(null != dateString){
      try{
        date = DATE_FORMAT.parse(dateString);
      }catch(ParseException pe){
        date = new Date();
      }
    }else{
      date = new Date();
    }
    return date;
  }


}