package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class Flight {
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonProperty("Departs")
  private Date departs;
  @JsonProperty("Tickets")
  private List tickets;
}
