package com.tusry.universityregistration.course.domain;

import com.tusry.universityregistration.enrollment.domain.Registration;
import com.tusry.universityregistration.enrollment.domain.RegistrationStatus;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private int capacity;
    private int enrolledCount;

    @Version
    private int version;

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

    public int getVersion() {
        return version;
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

    public void setVersion(int version) {
        this.version = version;
    }

    public void registerStudent(Long studentId){
        boolean already = registrations.stream().anyMatch(
                registration -> registration.getStudentId().equals(studentId) && registration.isActive());
        if (already){
            throw new IllegalArgumentException("Student already registered this class");
        }

        if(enrolledCount >= capacity){
            throw new IllegalArgumentException("Class is full, no availabel");
        }

        Registration registration = new Registration(this, studentId, RegistrationStatus.REGISTERED);
        registrations.add(registration);
        enrolledCount++;
    };

    public void cancelRegistration(Long studentId){
        registrations.stream().filter(
                registration -> registration.getStudentId().equals(studentId) && registration.isActive())
                .findFirst().ifPresent(registration -> {
                    registration.cancel();
                    enrolledCount--;
                });
    }



}
