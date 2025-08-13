package com.tusry.universityregistration.student.infrastructure;

import com.tusry.universityregistration.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
