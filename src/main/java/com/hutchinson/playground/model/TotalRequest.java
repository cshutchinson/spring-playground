package com.hutchinson.playground.model;

import java.util.List;


public class TotalRequest {
  private List<Ticket> tickets;

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public TotalRequest(List<Ticket> tickets) {
    this.tickets = tickets;
  }
}
