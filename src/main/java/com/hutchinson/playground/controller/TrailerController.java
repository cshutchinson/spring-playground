package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Trailer;
import com.hutchinson.playground.repo.ITrailerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trailers")
public class TrailerController {
  private final ITrailerRepository trailerRepository;

  public TrailerController(ITrailerRepository trailerRepository) {
    this.trailerRepository = trailerRepository;
  }

  @GetMapping("/{id}")
  public Trailer findTrailer(@PathVariable Long id) {
    return this.trailerRepository.findOne(id);
  }
}
