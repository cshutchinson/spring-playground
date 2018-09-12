package com.hutchinson.playground.controller;

import com.hutchinson.playground.config.SecurityConfig;
import com.hutchinson.playground.repo.EmployeeRepository;
import com.hutchinson.playground.service.EmployeeDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
@Import(SecurityConfig.class)
public class AdminControllerTest {
  MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @MockBean
  EmployeeDetailsService employeeDetailsService;

  @MockBean
  EmployeeRepository employeeRepository;

  @Before
  public void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithMockUser(roles = "MANAGER")
  public void getEmployees_worksForManagerRole() throws Exception {
    RequestBuilder request = get("/admin/employees");
    mvc
      .perform(request).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", roles = "USER")
  public void getEmployees_returns401ForUserRole() throws Exception {
    RequestBuilder request = get("/admin/employees");
    mvc.perform(request).andExpect(status().isForbidden());
  }

  @Test
  @WithAnonymousUser
  public void getEmployees_returns401ForAnonymous() throws Exception {
    RequestBuilder request = get("/admin/employees");
    mvc.perform(request).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getEmployees_worksForAdminRole() throws Exception {
    RequestBuilder request = get("/admin/employees");
    mvc
      .perform(request).andExpect(status().isOk());
  }
}