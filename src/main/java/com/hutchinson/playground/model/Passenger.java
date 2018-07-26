package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Passenger {
  @JsonProperty("FirstName")
  private String firstName;
  @JsonProperty("LastName")
  private String lastName;

  @JsonGetter("firstName")
  public String getFirstName() {
    return firstName;
  }
  @JsonSetter("firstName")
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  @JsonGetter("lastName")
  public String getLastName() {
    return lastName;
  }
  @JsonSetter("lastName")
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
