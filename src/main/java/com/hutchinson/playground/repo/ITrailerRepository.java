package com.hutchinson.playground.repo;

import com.hutchinson.playground.model.Trailer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrailerRepository extends CrudRepository<Trailer, Long> {
}
