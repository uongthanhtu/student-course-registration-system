package com.tusry.universityregistration.course.infrastructure;

import com.tusry.universityregistration.course.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
