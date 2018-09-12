package com.hutchinson.playground.repo;

import com.hutchinson.playground.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, Long> {
  List<Movie> findAll();
}
