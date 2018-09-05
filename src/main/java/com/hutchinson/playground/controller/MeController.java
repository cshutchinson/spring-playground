package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Employee;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

  @GetMapping("/me")
  public Employee getMe(@AuthenticationPrincipal Employee employee) {
    return employee;
  }
}
