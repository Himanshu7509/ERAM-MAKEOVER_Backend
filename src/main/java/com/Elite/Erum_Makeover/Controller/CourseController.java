package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.DTO.CourseRequestDTO;
import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> all(){
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable String id) {
        Course course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }
//    // ADMIN CRUD
//    @PostMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Course add(@RequestBody Course c){
//        return service.add(c);
//    }
@PostMapping(value = "/add-course", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Course> addCourse(
        @RequestPart("course") CourseRequestDTO courseDTO,
        @RequestPart("image") MultipartFile image) {

    return ResponseEntity.ok(courseService.addCourse(courseDTO, image));
}

    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Course update(@PathVariable String id,@RequestBody Course c)
    {
        return courseService.update(id,c);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
     public String delete(@PathVariable String id){
        courseService.delete(id);
        return "Course deleted successfully";
    }
}