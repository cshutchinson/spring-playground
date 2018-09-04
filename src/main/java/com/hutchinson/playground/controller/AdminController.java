package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Employee;
import com.hutchinson.playground.repo.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
  private final EmployeeRepository repo;

  public AdminController(EmployeeRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/employees")
  public Iterable<Employee> getEmployees() {
    return repo.findAll();
  }
}
