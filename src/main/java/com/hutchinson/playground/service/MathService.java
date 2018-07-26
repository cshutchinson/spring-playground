package com.hutchinson.playground.service;

import com.hutchinson.playground.model.AreaRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.PI;

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

  public String volume(Integer length, Integer width, Integer height){
    return String.format("The volume of a %dx%dx%d rectangle is %d",
      length, width, height, length*width*height);
  }

  public String area (AreaRequest areaRequest){
    if (!validAreaRequest(areaRequest)) return "Invalid";

    switch (areaRequest.getType()){
      case "rectangle":
        return String.format("Area of a %dx%d %s is %d",
              areaRequest.getWidth(),
              areaRequest.getHeight(),
              areaRequest.getType(),
              areaRequest.getWidth() * areaRequest.getHeight());
      case "circle":
        return String.format("Area of a %s with a radius of %d is %.5f",
              areaRequest.getType(),
              areaRequest.getRadius(),
              areaRequest.getRadius() * areaRequest.getRadius() * PI);
    }
    return "Invalid";
  }

  private boolean validAreaRequest(AreaRequest request){
    if (request.getType()==null || request.getType().isEmpty()){
      return false;
    }
    switch (request.getType()) {
      case "rectangle":
        return request.getHeight() != null && request.getWidth() != null &&
          request.getHeight() > 0 && request.getWidth() > 0;
      case "circle":
        return request.getRadius() != null && request.getRadius() > 0;
      default:
        return false;
    }
  }
}
