package com.hutchinson.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hutchinson.playground.View.Views;
import com.hutchinson.playground.model.Employee;
import com.hutchinson.playground.repo.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
  private final EmployeeRepository repo;

  public EmployeesController(EmployeeRepository repo) {
    this.repo = repo;
  }

  @GetMapping("")
  @JsonView(Views.NoSalary.class)
  public Iterable<Employee> getEmployees() {
    return repo.findAll();
  }
}
