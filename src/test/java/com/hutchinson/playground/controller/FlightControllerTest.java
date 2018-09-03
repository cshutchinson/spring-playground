package com.hutchinson.playground.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hutchinson.playground.model.Passenger;
import com.hutchinson.playground.model.Ticket;
import com.hutchinson.playground.model.TotalRequest;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
class FlightControllerTest {
  private MockMvc mvc = MockMvcBuilders.standaloneSetup(new FlightController()).build();


  @Test
  public void flight_shouldReturnExpectedJson() throws Exception {
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
  public void flights_shouldReturnExpectedListOfFlights() throws Exception {
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
  }

  @Test
  public void total_shouldReturnExpectedJsonResponse_withStringLiteral() throws Exception {
    MockHttpServletRequestBuilder request = post("/flights/tickets/total")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"},\"price\": 200},{\"passenger\": {\"firstName\": \"Name B\",\"lastName\": \"Name C\"},\"price\": 150}]}");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().string("{\"result\":350}"));
  }

  @Test
  void total_shouldReturnExpectedJsonResponse_withJacksonSerialization() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Passenger passenger0 = new Passenger("Some name", "Some other name");

    Passenger passenger1 = new Passenger("Name B", "Name C");

    Ticket ticket0 = new Ticket(passenger0, 200);

    Ticket ticket1 = new Ticket(passenger1, 150);

    TotalRequest requestData = new TotalRequest(asList(ticket0, ticket1));

    String json = objectMapper.writeValueAsString(requestData);

    MockHttpServletRequestBuilder request = post("/flights/tickets/total")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().string("{\"result\":350}"));
  }

  @Test
  void total_shouldReturnExpectedJsonResponse_withFileFixture() throws Exception {
    String json = getJSON("/TotalRequest.json");
    String responseJson = getJSON("/TotalResponse.json");

    MockHttpServletRequestBuilder request = post("/flights/tickets/total")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json);

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().json(responseJson));
  }

  private String getJSON(String path) throws Exception {
    URL url = this.getClass().getResource(path);
    return new String(Files.readAllBytes(Paths.get(url.getFile())));
  }
  }