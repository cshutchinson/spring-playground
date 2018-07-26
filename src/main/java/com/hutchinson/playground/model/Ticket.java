package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
  @JsonProperty("Passenger")
  private Passenger passenger;
  @JsonProperty("Price")
  private Integer price;
}
