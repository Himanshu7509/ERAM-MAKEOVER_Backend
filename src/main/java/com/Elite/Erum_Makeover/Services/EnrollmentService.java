package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.Model.Course;
import com.Elite.Erum_Makeover.Model.Enrollment;
import com.Elite.Erum_Makeover.Repository.CourseRepository;
import com.Elite.Erum_Makeover.Repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {


    private final EnrollmentRepository repo;
    private final CourseRepository courseRepository;  // 👈 get course details
    private final EmailService emailService;          // 👈 inject email service

    public Enrollment enroll(
            String userId,
            String fullName,
            String email,
            String courseId,
            String phone,
            String message
    ){

        Optional<Enrollment> existing =
                repo.findByUserIdAndCourseId(userId, courseId);

        if(existing.isPresent()){
            throw new RuntimeException("Already enrolled in this course");
        }

        // 🔎 Get course details
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment e = new Enrollment();
        e.setUserId(userId);
        e.setFullName(fullName);
        e.setEmail(email);
        e.setCourseId(courseId);
        e.setPhone(phone);
        e.setMessage(message);
        e.setEnrolledAt(LocalDateTime.now());

        Enrollment savedEnrollment = repo.save(e);

        emailService.sendCourseEnrollmentEmail(
                fullName,
                email,
                course.getTitle(),
                course.getPrice(),
                course.getDuration(),
                course.getLevel()
        );

        return savedEnrollment;
    }

    public List<Enrollment> all(){
        return repo.findAll();
    }
}
