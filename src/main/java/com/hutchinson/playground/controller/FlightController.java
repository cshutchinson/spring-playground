package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Flight;
import com.hutchinson.playground.model.Passenger;
import com.hutchinson.playground.model.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.util.Arrays.asList;


@RestController
@RequestMapping("/flights")
public class FlightController {
  @GetMapping
  public List<Flight> flights() throws Exception{
    return getFlights();
  }

  @GetMapping("/flight")
  public Flight flight() throws Exception{
    return getFlight();
  }

  private Date dateGen(String dateString) throws Exception{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = sdf.parse(dateString);
    System.out.println(date);
    return date;
  }

  private Flight getFlight() throws Exception {
    Passenger passenger = Passenger.builder()
      .firstName("Some name")
      .lastName("Some other name")
      .build();

    Ticket ticket = Ticket.builder()
      .passenger(passenger)
      .price(200)
      .build();

    return Flight.builder()
      .departs(dateGen("2017-04-21 14:34"))
      .tickets(Collections.singletonList(ticket))
      .build();
  }

  private List<Flight> getFlights() throws Exception {
    Passenger passenger0 = Passenger.builder()
      .firstName("Some name")
      .lastName("Some other name")
      .build();

    Ticket ticket0 = Ticket.builder()
      .passenger(passenger0)
      .price(200)
      .build();

    Flight flight0 = Flight.builder()
      .departs(dateGen("2017-04-21 14:34"))
      .tickets(Collections.singletonList(ticket0))
      .build();

    Passenger passenger1 = Passenger.builder()
      .firstName("Some other name")
      .lastName(null)
      .build();

    Ticket ticket1 = Ticket.builder()
      .passenger(passenger1)
      .price(400)
      .build();

    Flight flight1 = Flight.builder()
      .departs(dateGen("2017-04-21 14:34"))
      .tickets(Collections.singletonList(ticket1))
      .build();

    return asList(flight0, flight1);
  }
}

