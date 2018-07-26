package com.hutchinson.playground.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathController.class)
class FlightControllerTest {
//  private MathService mathService = new MathService();
  private MockMvc mvc = MockMvcBuilders.standaloneSetup(new FlightController()).build();;

  @BeforeEach
  void setup() {

  }

  @Test
  void flight_shouldReturnExpectedJson() throws Exception {
    this.mvc.perform(get("/flights/flight")
      .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
      .contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
      .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
      .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")))
      .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
  }

  @Test
  void flights_shouldReturnExpectedListOfFlights() throws Exception {
    this.mvc.perform(get("/flights")
      .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
      .contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
      .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Some name")))
      .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Some other name")))
      .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
      .andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
      .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Some other name")))
      .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
//    .andExpect(jsonPath("$[1].Tickets[0].Passenger.LastName", is(nullValue())))
  }
}