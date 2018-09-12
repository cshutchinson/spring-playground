package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.ImdbMovie;
import com.hutchinson.playground.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imbdbmovies")
public class ImdbController {
  private MovieService movieService;

  public ImdbController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("")
  public List<ImdbMovie> findMovies(@RequestParam String q){
    return movieService.getMovies(q);
  }
}
