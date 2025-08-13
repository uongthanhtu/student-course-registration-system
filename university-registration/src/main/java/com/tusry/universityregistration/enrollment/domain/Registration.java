package com.tusry.universityregistration.enrollment.domain;

import com.tusry.universityregistration.course.domain.CourseClass;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_class_id","student_id"}))
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "student_id")
    Long studentId;

    @Enumerated(EnumType.STRING)
    RegistrationStatus status;

    LocalDateTime registeredAt;

    @ManyToOne
    @JoinColumn(name = "course_class_id")
    private CourseClass courseClass;

    public Registration() {
    }

    public Registration(CourseClass courseClass , Long studentId, RegistrationStatus status) {
        this.courseClass = courseClass;
        this.studentId = studentId;
        this.status = status;
        this.registeredAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public boolean isActive() {
        return this.status == RegistrationStatus.REGISTERED || this.status == RegistrationStatus.WAITING;
    }
    public void cancel(){
        this.status = RegistrationStatus.CANCELLED;
    }
}
