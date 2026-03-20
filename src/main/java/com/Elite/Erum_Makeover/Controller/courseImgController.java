package com.Elite.Erum_Makeover.Controller;

import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Repository.CourseRepository;
import com.Elite.Erum_Makeover.Services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class courseImgController

{
    private final CourseRepository courseRepository;
    private final S3Service s3Service;

    @PostMapping("upload-image/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> uploadCourseImage(
            @PathVariable String courseId,
            @RequestParam("file") MultipartFile file
    ) {

        // 🔥 Find Course
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Image image = s3Service.uploadFile(file, "ErumMakeover/Courses");

        course.setImageUrl(image.getImageUrl());

        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }
}
