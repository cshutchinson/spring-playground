package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.AreaRequest;
import com.hutchinson.playground.service.MathService;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping(value = "/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String area(AreaRequest areaRequest){
    return mathService.area(areaRequest);
  }
}
