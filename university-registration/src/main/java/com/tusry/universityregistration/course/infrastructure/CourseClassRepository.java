package com.tusry.universityregistration.course.infrastructure;

import com.tusry.universityregistration.course.domain.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {
}
