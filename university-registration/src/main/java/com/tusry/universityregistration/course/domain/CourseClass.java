package com.tusry.universityregistration.course.domain;

import com.tusry.universityregistration.enrollment.domain.Registration;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
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

    public CourseClass(String courseCode, int capacity) {
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setEnrolledCount(int enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

    public void registerStudent(Long studentId){
        boolean already = registrations.stream().anyMatch(registration -> {
            registration.
        });
    }



}
