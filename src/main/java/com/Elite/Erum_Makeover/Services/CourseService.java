package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.DTO.CourseRequestDTO;
import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Model.Image;
import com.Elite.Erum_Makeover.Repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final S3Service s3Service;

    public Course add(Course c){ return courseRepository.save(c); }

    public List<Course> getAll(){ return courseRepository.findAll(); }

    public Course update(String id, Course c){

        Course bookCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        bookCourse.setTitle(c.getTitle());
        bookCourse.setDescription(c.getDescription());
        bookCourse.setPrice(c.getPrice());


        // NEW FIELDS
        bookCourse.setWhatYouWillLearn(c.getWhatYouWillLearn());
        bookCourse.setDuration(c.getDuration());
        bookCourse.setLevel(c.getLevel());
        bookCourse.setImageUrl(c.getImageUrl());
        return courseRepository.save(bookCourse);
    }
public Course addCourse(CourseRequestDTO dto, MultipartFile image){

    Image uploadedImage = s3Service.uploadFile(image, "courses");
    Course course = new Course();

    course.setTitle(dto.getTitle());
    course.setDescription(dto.getDescription());
    course.setPrice(dto.getPrice());
    course.setDuration(dto.getDuration());
    course.setLevel(dto.getLevel());
    course.setWhatYouWillLearn(dto.getWhatYouWillLearn());

    course.setImageUrl(uploadedImage.getImageUrl());
    return courseRepository.save(course);
}
    public Course getById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }
    public void delete(String id){ courseRepository.deleteById(id); }
}