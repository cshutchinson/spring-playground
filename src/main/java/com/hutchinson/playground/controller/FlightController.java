package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

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

  @PostMapping("/tickets/total")
  public TotalResponse total(@RequestBody TotalRequest request) throws Exception{
    System.out.println("request = " + request);
    return getTotal(request);
  }

  private TotalResponse getTotal(TotalRequest request){
    List<Ticket> tickets = request.getTickets();
    int sum = tickets.stream().mapToInt(Ticket::getPrice).sum();

    return TotalResponse.builder()
      .result(sum)
      .build();
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

