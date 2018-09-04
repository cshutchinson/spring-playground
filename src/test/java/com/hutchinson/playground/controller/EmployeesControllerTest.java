package com.hutchinson.playground.controller;

import com.hutchinson.playground.config.SecurityConfig;
import com.hutchinson.playground.repo.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
@Import(SecurityConfig.class)
public class EmployeesControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private EmployeeRepository employeeRepository;

  @Test
  public void testWithUser() throws Exception {
    MockHttpServletRequestBuilder request = get("/employees").with(user("user").roles("EMPLOYEE"));
    mvc.perform(request).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "EMPLOYEE")
  public void indexAllowsEmployeeUsers() throws Exception {
    RequestBuilder request = get("/employees");
    mvc.perform(request).andExpect(status().isOk());
  }


}