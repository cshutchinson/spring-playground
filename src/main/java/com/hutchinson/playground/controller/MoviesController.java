package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Movie;
import com.hutchinson.playground.repo.IMovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/movies")
public class MoviesController {

  private final IMovieRepository repository;

  public MoviesController(IMovieRepository repository) {
    this.repository = repository;
  }

  @GetMapping("")
  public List<Movie> all() {
    List<Movie> movies = this.repository.findAll();

    movies
      .forEach(movie -> {
        movie.add(linkTo(MoviesController.class)
          .slash(movie.getMovieId().toString())
          .withSelfRel());
        movie.add(linkTo(methodOn(TrailerController.class).findTrailer(movie.getMovieId())).withRel("trailer"));
      });

    return movies;
  }

}
