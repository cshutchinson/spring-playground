package com.hutchinson.playground.controller;

import com.hutchinson.playground.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathController.class)
class MathControllerTest {
  private MathService mathService = new MathService();
  private MockMvc mvc = MockMvcBuilders.standaloneSetup(new MathController(mathService)).build();

  @BeforeEach
  void setup() {

  }

  @Test
  void mathPiEndpoint_shouldReturnPi() throws Exception {
      this.mvc.perform(get("/math/pi")
          .accept(MediaType.TEXT_PLAIN))
          .andExpect(status().isOk())
          .andExpect(content().string("3.141592653589793"));
  }

  @Test
  void mathCalculate_shouldReturnExpectedString_forOperationAdd() throws Exception {
      this.mvc.perform(get("/math/calculate")
          .param("operation", "add")
          .param("x", "4")
          .param("y", "6"))
          .andExpect(status().isOk())
          .andExpect(content().string("4 + 6 = 10"));
  }

  @Test
  void mathCalculate_shouldReturnExpectedString_forOperationMultiply() throws Exception {
      this.mvc.perform(get("/math/calculate")
          .param("operation", "multiply")
          .param("x", "4")
          .param("y", "6"))
          .andExpect(status().isOk())
          .andExpect(content().string("4 * 6 = 24"));
  }

  @Test
  void mathCalculate_shouldReturnExpectedString_forOperationSubtract() throws Exception {
      this.mvc.perform(get("/math/calculate")
          .param("operation", "subtract")
          .param("x", "4")
          .param("y", "6"))
          .andExpect(status().isOk())
          .andExpect(content().string("4 - 6 = -2"));
  }

  @Test
  void mathCalculate_shouldReturnExpectedString_forOperationDivide() throws Exception {
      this.mvc.perform(get("/math/calculate")
          .param("operation", "divide")
          .param("x", "30")
          .param("y", "5"))
          .andExpect(status().isOk())
          .andExpect(content().string("30 / 5 = 6"));
  }

  @Test
  void mathCalculate_shouldReturnExpectedString_forOperationOmitted() throws Exception {
      this.mvc.perform(get("/math/calculate")
          .param("x", "30")
          .param("y", "5"))
          .andExpect(status().isOk())
          .andExpect(content().string("30 + 5 = 35"));
  }

  @Test
  void mathSum_shouldReturnExpectedString_forMultipleParameters() throws Exception{
    this.mvc.perform(post("/math/sum")
        .param("n", "4")
        .param("n", "5")
        .param("n", "6"))
        .andExpect(status().isOk())
        .andExpect(content().string("4 + 5 + 6 = 15"));
  }

  @Test
  void mathVolume_shouldReturnExpectedString_forGivenGetRequest() throws Exception {
    this.mvc.perform(get("/math/volume/6/7/8"))
      .andExpect(status().isOk())
      .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
  }

  @Test
  void mathVolume_shouldReturnExpectedString_forGivenPostRequest() throws Exception {
    this.mvc.perform(post("/math/volume/6/7/8"))
      .andExpect(status().isOk())
      .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
  }

  @Test
  void mathVolume_shouldReturnExpectedString_forGivenPatchRequest() throws Exception {
    this.mvc.perform(patch("/math/volume/6/7/8"))
      .andExpect(status().isOk())
      .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
  }

  @Test
  void mathArea_shouldReturnExpectedString_forGivenRectangleRequest() throws Exception{
    MockHttpServletRequestBuilder request = post("/math/area")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("type", "rectangle")
      .param("width", "4")
      .param("height", "7");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().string("Area of a 4x7 rectangle is 28"));
  }

  @Test
  void mathArea_shouldReturnExpectedString_forGivenCircleRequest() throws Exception{
    MockHttpServletRequestBuilder request = post("/math/area")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("type", "circle")
      .param("radius", "4");

    this.mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
  }
}