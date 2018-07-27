package com.hutchinson.playground.repo;

import com.hutchinson.playground.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

}
