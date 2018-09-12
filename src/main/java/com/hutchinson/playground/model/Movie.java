package com.hutchinson.playground.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie extends ResourceSupport {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long movieId;
  private String title;

  public Movie(String title) {
    this.title = title;
  }

  public Movie() {
  }

  public Long getMovieId() {
    return movieId;
  }

  public void setMovieId(Long movieId) {
    this.movieId = movieId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
