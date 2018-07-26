package com.hutchinson.playground.service;

import com.hutchinson.playground.model.AreaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;

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

  @Test
  void volume_returnsCorrectString(){
    String actual = mathService.volume(6, 7, 8);
    String expected = "The volume of a 6x7x8 rectangle is 336";

    assertEquals(expected, actual, "Should return: The volume of a 6x7x8 rectangle is 336");
  }

  @Test
  void area_forTypeRectangle_returnsCorrectString(){
    AreaRequest areaRequest = new AreaRequest("rectangle", null, 4, 7);
    String actual = mathService.area(areaRequest);
    String expected = "Area of a 4x7 rectangle is 28";

    assertEquals(expected, actual, expected);
  }

  @Test
  void area_forTypeCircle_returnsCorrectString(){
    AreaRequest areaRequest = new AreaRequest("circle", 4, null, null);
    String actual = mathService.area(areaRequest);
    String expected = "Area of a circle with a radius of 4 is 50.26548";

    assertEquals(expected, actual, expected);
  }

  @Test
  void area_forInvalidRequest_returnsCorrectString_whenAllParamsNull(){
    AreaRequest areaRequest = new AreaRequest(null, null, null, null);
    String actual = mathService.area(areaRequest);
    String expected = "Invalid";

    assertEquals(expected, actual);
  }

  @Test
  void area_forInvalidRequest_returnsCorrectString_whenAllParamsEmptyAndZero(){
    AreaRequest areaRequest = new AreaRequest("", 0,0,0);
    String actual = mathService.area(areaRequest);
    String expected = "Invalid";

    assertEquals(expected, actual);
  }

  @Test
  void area_forInvalidRequest_returnsCorrectString_whenTypeDefinedRectangleButSizesAreZero(){
    AreaRequest areaRequest = new AreaRequest("rectangle", 0,0,0);
    String actual = mathService.area(areaRequest);
    String expected = "Invalid";

    assertEquals(expected, actual);
  }

  @Test
  void area_forInvalidRequest_returnsCorrectString_whenTypeDefinedCircleButSizesAreZero(){
    AreaRequest areaRequest = new AreaRequest("circle", 0,0,0);
    String actual = mathService.area(areaRequest);
    String expected = "Invalid";

    assertEquals(expected, actual);
  }
}