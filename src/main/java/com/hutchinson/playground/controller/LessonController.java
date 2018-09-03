package com.hutchinson.playground.controller;

import com.hutchinson.playground.model.Lesson;
import com.hutchinson.playground.repo.LessonRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {
  private final LessonRepository lessonRepository;

  public LessonController(LessonRepository lessonRepository) {
    this.lessonRepository = lessonRepository;
  }

  @GetMapping("")
  public Iterable<Lesson> all(){
    return this.lessonRepository.findAll();
  }

  @PostMapping("")
  public Lesson create(@RequestBody Lesson lesson){
    return this.lessonRepository.save(lesson);
  }

  @GetMapping("/{id}")
  public Lesson getOne(@PathVariable Long id){
    return this.lessonRepository.findOne(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    this.lessonRepository.delete(id);
  }

  @PatchMapping("/{id}")
  public Lesson patch(@PathVariable Long id, @RequestBody Lesson lesson){
    Lesson optionalLesson = this.lessonRepository.findOne(id);
    if(optionalLesson != null){
      optionalLesson.setTitle(lesson.getTitle());
      optionalLesson.setDeliveredOn(lesson.getDeliveredOn());
    }

    return this.lessonRepository.save(optionalLesson);
  }

  @GetMapping("/find/{title}")
  public Lesson findByTitle(@PathVariable String title){
    return lessonRepository.findByTitle(title);
  }

  @GetMapping("/between")
  public Iterable<Lesson> between(@RequestParam String date1,
                              @RequestParam String date2){
    return lessonRepository.findBetweenDateRange(date1, date2);
  }
}
