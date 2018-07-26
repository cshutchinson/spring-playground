package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Ticket {
  @JsonProperty("Passenger")
  private Passenger passenger;
  @JsonProperty("Price")
  private Integer price;

  @JsonGetter("passenger")
  public Passenger getPassenger() {
    return passenger;
  }
  @JsonSetter("passenger")
  public void setPassenger(Passenger passenger) {
    this.passenger = passenger;
  }
  @JsonGetter("price")
  public Integer getPrice() {
    return price;
  }
  @JsonSetter("price")
  public void setPrice(Integer price) {
    this.price = price;
  }
}
