package com.hutchinson.playground.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trailers")
public class Trailer extends ResourceSupport{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long trailerId;
  private Date releaseDate;
  private String rating;
  private String title;

  public Trailer(Date releaseDate, String rating, String title) {
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.title = title;
  }

  public Trailer() {
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
