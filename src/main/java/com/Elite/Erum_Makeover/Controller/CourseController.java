package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.DTO.CourseResponseDTO;
import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Services.CourseService;
import com.Elite.Erum_Makeover.Services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService service;
    private final S3Service s3Service;

    // Get All Courses
    @GetMapping
    public List<CourseResponseDTO> all() {
        List<Course> courses = service.getAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Course By ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getById(@PathVariable String id) {
        Course course = service.getById(id);
        return ResponseEntity.ok(convertToDTO(course));
    }

    // Convert Entity -> DTO
    private CourseResponseDTO convertToDTO(Course course) {

        CourseResponseDTO dto = new CourseResponseDTO();

        dto.setCourseId(course.getCourseId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setDuration(course.getDuration());
        dto.setLevel(course.getLevel());
        dto.setWhatYouWillLearn(course.getWhatYouWillLearn());

        // assuming Course model has imageUrl stored
        dto.setImageUrl(course.getImageUrl());

        return dto;
    }

    // ADMIN CREATE COURSE
    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> add(@RequestBody Course c) {

        Course savedCourse = service.add(c);

        return ResponseEntity.ok(convertToDTO(savedCourse));
    }

    // Upload Course Image
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file") MultipartFile file) {

        Image image = s3Service.uploadFile(file, "courseImages");

        return ResponseEntity.ok(image.getImageUrl());
    }

    // Update Course
    @PutMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseResponseDTO> update(
            @PathVariable String id,
            @RequestBody Course c) {

        Course updatedCourse = service.update(id, c);

        return ResponseEntity.ok(convertToDTO(updatedCourse));
    }

    // Delete Course
    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "Course deleted successfully";
    }
}