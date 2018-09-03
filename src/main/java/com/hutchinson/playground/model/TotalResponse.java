package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TotalResponse {
  @JsonFormat(shape = JsonFormat.Shape.SCALAR)
  private Integer result;

  public Integer getResult() {
    return result;
  }

  public void setResult(Integer result) {
    this.result = result;
  }

  public TotalResponse(Integer result) {

    this.result = result;
  }

  public TotalResponse() {

  }
}