package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repo;

    public Course add(Course c){ return repo.save(c); }

    public List<Course> getAll(){ return repo.findAll(); }

    public Course update(String id, Course c){

        Course bookCourse = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        bookCourse.setTitle(c.getTitle());
        bookCourse.setDescription(c.getDescription());
        bookCourse.setPrice(c.getPrice());


        // NEW FIELDS
        bookCourse.setWhatYouWillLearn(c.getWhatYouWillLearn());
        bookCourse.setDuration(c.getDuration());
        bookCourse.setLevel(c.getLevel());
        bookCourse.setImageUrl(c.getImageUrl());
        return repo.save(bookCourse);


    }

    public void delete(String id){ repo.deleteById(id); }
}