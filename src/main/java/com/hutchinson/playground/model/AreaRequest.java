package com.hutchinson.playground.model;

public class AreaRequest {
  private String type;
  private Integer radius;
  private Integer width;
  private Integer height;

  public AreaRequest(String type, Integer radius, Integer width, Integer height) {
    this.type = type;
    this.radius = radius;
    this.width = width;
    this.height = height;
  }

  public AreaRequest(String type, Integer width, Integer height) {
    this.type = type;
    this.width = width;
    this.height = height;
  }

  public AreaRequest(String type, Integer radius) {
    this.type = type;
    this.radius = radius;
  }

  public AreaRequest() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getRadius() {
    return radius;
  }

  public void setRadius(Integer radius) {
    this.radius = radius;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  @Override
  public String toString() {
    return "AreaRequest{" +
      "type='" + type + '\'' +
      ", radius=" + radius +
      ", width=" + width +
      ", height=" + height +
      '}';
  }
}
