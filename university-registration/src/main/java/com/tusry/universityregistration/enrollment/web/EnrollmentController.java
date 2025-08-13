package com.tusry.universityregistration.enrollment.web;

import com.tusry.universityregistration.enrollment.application.EnrollmentService;
import com.tusry.universityregistration.enrollment.web.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/{classId}/register")
    public ResponseEntity<?> registerEnrollment(@PathVariable(name = "classId") long classId, @RequestBody RegisterRequest request) {
        try {
            enrollmentService.registerToClass(classId, request.getStudentId());
            return ResponseEntity.ok("Enrollment successfully registered:" + classId );

        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Bad Request:" + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/{classId}/cancel")
    public ResponseEntity<?> cancelClass(@PathVariable Long classId, @RequestBody RegisterRequest request) {
        try {
            enrollmentService.cancelRegistration(classId,
                    request.getStudentId());
            return ResponseEntity.ok("Cancel successfully" + classId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Bad Request: " +
                    e.getMessage());
        }
    }



}
