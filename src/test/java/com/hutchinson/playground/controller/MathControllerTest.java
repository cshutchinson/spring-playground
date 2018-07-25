package com.hutchinson.playground.controller;

import com.hutchinson.playground.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathController.class)
class MathControllerTest {
  private MathService mathService = new MathService();
  private MockMvc mvc = MockMvcBuilders.standaloneSetup(new MathController(mathService)).build();;

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

  ///math/sum?n=4&n=5&n=6 should render the string 4 + 5 + 6 = 15
  @Test
  void mathSum_shouldReturnExpectedString_forMultipleParameters() throws Exception{
      this.mvc.perform(post("/math/sum")
          .param("n", "4")
          .param("n", "5")
          .param("n", "6"))
          .andExpect(status().isOk())
          .andExpect(content().string("4 + 5 + 6 = 15"));
  }
}