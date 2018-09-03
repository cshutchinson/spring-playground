package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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

  @PostMapping("/tickets/total")
  public TotalResponse total(@RequestBody TotalRequest request) throws Exception{
    System.out.println("request = " + request);
    return getTotal(request);
  }

  private TotalResponse getTotal(TotalRequest request){
    List<Ticket> tickets = request.getTickets();
    int sum = tickets.stream().mapToInt(Ticket::getPrice).sum();

    return new TotalResponse(sum);
  }

  private Date dateGen(String dateString) throws Exception{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date date = sdf.parse(dateString);
    System.out.println(date);
    return date;
  }

  private Flight getFlight() throws Exception {
    Passenger passenger = new Passenger("Some name", "Some other name");

    Ticket ticket = new Ticket(passenger, 200);

    return new Flight(dateGen("2017-04-21 14:34"), asList(ticket));
  }

  private List<Flight> getFlights() throws Exception {

    Passenger passenger0 = new Passenger("Some name", "Some other name");

    Ticket ticket0 = new Ticket(passenger0, 200);

    Flight flight0 = new Flight(dateGen("2017-04-21 14:34"), asList(ticket0));

    Passenger passenger1 = new Passenger("Some other name", null);

    Ticket ticket1 = new Ticket(passenger1, 400);

    Flight flight1 = new Flight(dateGen("2017-04-21 14:34"), asList(ticket1));

    return asList(flight0, flight1);
  }
}

