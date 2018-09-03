package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;


public class Flight {
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonProperty("Departs")
  private Date departs;
  @JsonProperty("Tickets")
  private List tickets;

  public Flight(Date departs, List tickets) {
    this.departs = departs;
    this.tickets = tickets;
  }

  public Flight(Date departs) {

    this.departs = departs;
  }

  public Date getDeparts() {
    return departs;
  }

  public void setDeparts(Date departs) {
    this.departs = departs;
  }

  public List getTickets() {
    return tickets;
  }

  public void setTickets(List tickets) {
    this.tickets = tickets;
  }
}
