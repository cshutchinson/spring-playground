package com.hutchinson.playground.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ImdbMovieResponse {
  @JsonProperty("Search")
  private List<ImdbMovie> search;

  public ImdbMovieResponse() {
  }

  public ImdbMovieResponse(List<ImdbMovie> search) {
    this.search = search;
  }

  public List<ImdbMovie> getSearch() {
    return search;
  }

  public void setSearch(List<ImdbMovie> search) {
    this.search = search;
  }
}