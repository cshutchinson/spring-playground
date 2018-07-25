package com.hutchinson.playground.controller;

import com.hutchinson.playground.service.MathService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/math")
public class MathController {
    private MathService mathService;

  public MathController(MathService mathService) {
    this.mathService = mathService;
  }

  @GetMapping("/pi")
    public String pi() {
        return "3.141592653589793";
    }

  @GetMapping("/calculate")
  public String calculate(
          @RequestParam(required = false, defaultValue = "add") String operation,
          @RequestParam Integer x,
          @RequestParam Integer y) {
    return mathService.calculate(operation, x, y);
  }

  @PostMapping("/sum")
  public String sum(@RequestParam LinkedMultiValueMap<String, String> parameterMap) {
    return mathService.sum(parameterMap);
  }

  @RequestMapping("/volume/{length}/{width}/{height}")
  public String volume(@PathVariable Integer length,
                       @PathVariable Integer width,
                       @PathVariable Integer height){
    return mathService.volume(length, width, height);
  }
}
