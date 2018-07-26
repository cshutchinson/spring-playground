package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Passenger {
  @JsonProperty("FirstName")
  private String firstName;
  @JsonProperty("LastName")
  private String lastName;
}
