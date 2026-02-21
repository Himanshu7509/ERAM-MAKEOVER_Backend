package com.Elite.Erum_Makeover.Services;

import com.Elite.Erum_Makeover.Model.Enrollment;
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

        Enrollment e = new Enrollment();
        e.setUserId(userId);
        e.setFullName(fullName);
        e.setEmail(email);
        e.setCourseId(courseId);
        e.setPhone(phone);
        e.setMessage(message);
        e.setEnrolledAt(LocalDateTime.now());

        return repo.save(e);
    }

    public List<Enrollment> all(){
        return repo.findAll();
    }
}