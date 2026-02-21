package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    public List<Course> all(){
        return service.getAll();
    }

    // ADMIN CRUD
    @PostMapping("/admin")
    @PreAuthorize("ADMIN")
    public Course add(@RequestBody Course c){
        return service.add(c);
    }

    @PutMapping("/admin/{id}")
    @PreAuthorize("ADMIN")
    public Course update(@PathVariable String id,@RequestBody Course c){
        return service.update(id,c);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("ADMIN")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Course deleted successfully";
    }
}