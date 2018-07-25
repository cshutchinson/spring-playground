package com.hutchinson.playground.service;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MathService {

  public String calculate(String operation, Integer x, Integer y){
    String result;

    switch(operation){
      case "add":
        result = String.format("%d + %d = %d", x, y, x+y );
        break;

      case "multiply":
        result = String.format("%d * %d = %d", x, y, x*y);
        break;

      case "subtract":
        result = String.format("%d - %d = %d", x, y, x-y);
        break;

      case "divide":
        result = String.format("%d / %d = %d", x, y, x/y);
        break;

      default:
        result = String.format("%d + %d = %d", x, y, x+y );
    }
    return result;
  }

  public String sum(LinkedMultiValueMap<String, String> params) {
    String sumInputs = "";
    Integer sumResult = 0;

    List<String> paramsList = params.get("n");

    if (paramsList != null) {
      sumInputs = paramsList.stream()
        .collect(Collectors.joining(" + "));

      sumResult = paramsList.stream()
        .mapToInt(Integer::parseInt).sum();
    }

    return String.format("%s = %d", sumInputs, sumResult);
  }
}
