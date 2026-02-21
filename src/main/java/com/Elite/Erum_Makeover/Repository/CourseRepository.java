package com.Elite.Erum_Makeover.Repository;

import com.Elite.Erum_Makeover.Model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course,String> {}