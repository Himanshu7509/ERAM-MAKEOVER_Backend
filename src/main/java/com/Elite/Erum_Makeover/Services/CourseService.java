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

        Course old = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        old.setTitle(c.getTitle());
        old.setDescription(c.getDescription());
        old.setPrice(c.getPrice());


        // NEW FIELDS
        old.setWhatYouWillLearn(c.getWhatYouWillLearn());
        old.setDuration(c.getDuration());
        old.setLevel(c.getLevel());
        old.setImageUrl(c.getImageUrl());


        return repo.save(old);
    }

    public void delete(String id){ repo.deleteById(id); }
}