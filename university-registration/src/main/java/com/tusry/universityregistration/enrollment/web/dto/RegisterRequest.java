package com.tusry.universityregistration.enrollment.web.dto;

public class RegisterRequest {
    private Long studentId;

    public RegisterRequest(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
