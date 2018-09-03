package com.hutchinson.playground.repo;

import com.hutchinson.playground.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {

  Lesson findByTitle(String title);

  @Query("select l from Lesson l where (l.deliveredOn between :start and :end)")
  Iterable<Lesson> findBetweenDateRange(@Param("start") String start,
                                        @Param("end") String end);
}
