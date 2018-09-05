package com.hutchinson.playground.config;

import com.hutchinson.playground.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  EmployeeDetailsService employeeDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().mvcMatchers("/flights/**", "/math/**", "/words/**", "/lessons/**", "/movies/**", "/favorites/**").permitAll();
    http.authorizeRequests().mvcMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER");
    http.httpBasic();
    http.authorizeRequests().anyRequest().authenticated();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // this line is the one that changes
    auth.userDetailsService(employeeDetailsService);
  }
}
