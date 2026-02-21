package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends MongoRepository<Enrollment,String> {
    List<Enrollment> findByUserId(String userId);
    Optional<Enrollment> findByUserIdAndCourseId(String userId, String courseId);
}
