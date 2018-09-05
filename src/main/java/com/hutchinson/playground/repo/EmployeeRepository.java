package com.hutchinson.playground.repo;

import com.hutchinson.playground.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
  Employee findByUsername(String username);
}
