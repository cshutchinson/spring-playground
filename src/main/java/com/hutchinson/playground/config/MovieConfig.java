package com.hutchinson.playground.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("movie")
public class MovieConfig {
  private String i;
  private String apikey;
  private String host;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getI() {
    return i;
  }

  public void setI(String i) {
    this.i = i;
  }

  public String getApikey() {
    return apikey;
  }

  public void setApikey(String apikey) {
    this.apikey = apikey;
  }
}
