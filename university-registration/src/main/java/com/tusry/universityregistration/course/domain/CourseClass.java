package com.tusry.universityregistration.course.domain;

import com.tusry.universityregistration.enrollment.domain.Registration;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String courseCode;
    int capacity;
    int enrolledCount;

    @OneToMany(mappedBy = "courseClass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Registration> registrations = new ArrayList<>();

    public CourseClass() {
    }

    public CourseClass(String courseCode, int capacity, int enrolledCount) {
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public void registerStudent( Long studentId){
        
    }


}
