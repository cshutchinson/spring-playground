package com.hutchinson.playground.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathServiceTest {
  private MathService mathService = new MathService();

  @BeforeEach
  void setup() {

  }

  @Test
  void calculate_returnsCorrectStringForOperationAdd() {
    String actual = mathService.calculate("add", 4, 6);
    String expected = "4 + 6 = 10";

    assertEquals(expected, actual, "Add 4 + 6 should equal 10");
  }

  @Test
  void calculate_returnsCorrectStringForOperationMultiply() {
    String actual = mathService.calculate("multiply", 4, 6);
    String expected = "4 * 6 = 24";

    assertEquals(expected, actual, "Multiply 4 * 6 should equal 24");
  }

  @Test
  void calculate_returnsCorrectStringForOperationSubtract() {
    String actual = mathService.calculate("subtract", 4, 6);
    String expected = "4 - 6 = -2";

    assertEquals(expected, actual, "Subtract 4 - 6 should equal -2");
  }

  @Test
  void calculate_returnsCorrectStringForOperationDivide() {
    String actual = mathService.calculate("divide", 30, 2);
    String expected = "30 / 2 = 15";

    assertEquals(expected, actual, "Divide 30 / 2 should equal 15");
  }

  @Test
  void calculate_returnsCorrectStringForOperationOmitted() {
    String actual = mathService.calculate("", 30, 2);
    String expected = "30 + 2 = 32";

    assertEquals(expected, actual, "Add 30 + 2 should equal 32");
  }

  @Test
  void sum_returnsCorrectString(){
    LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.add("n", "10");
    multiValueMap.add("n", "20");
    multiValueMap.add("n", "30");
    String actual = mathService.sum(multiValueMap);

    String expected = "10 + 20 + 30 = 60";

    assertEquals(expected, actual, "Add 10 + 20 + 30 = 60");
    }
}