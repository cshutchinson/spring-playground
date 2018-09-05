package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.hutchinson.playground.View.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(Views.NoSalary.class)
  private Long id;
  @JsonView(Views.NoSalary.class)
  private String name;
  @JsonView(Views.Salary.class)
  private int salary;
  @JsonView(Views.NoSalary.class)
  private Long mangerId;

  public Employee() {
  }

  public Employee(Long id, String name, int salary, Long mangerId) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    this.mangerId = mangerId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public Long getMangerId() {
    return mangerId;
  }

  public void setMangerId(Long mangerId) {
    this.mangerId = mangerId;
  }
}
