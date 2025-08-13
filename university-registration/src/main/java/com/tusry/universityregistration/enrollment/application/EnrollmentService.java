package com.tusry.universityregistration.enrollment.application;

import com.tusry.universityregistration.course.domain.Course;
import com.tusry.universityregistration.course.domain.CourseClass;
import com.tusry.universityregistration.course.infrastructure.CourseClassRepository;
import com.tusry.universityregistration.student.infrastructure.StudentRepository;
import io.lettuce.core.RedisClient;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class EnrollmentService {
    @Autowired
    private CourseClassRepository courseClassRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RedissonClient redissonClient;

    @Transactional
    public void registerToClass (Long classId, Long studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new IllegalArgumentException("Student does not exist");
        }

        RLock lock = redissonClient.getLock("class:" + classId);
        boolean locked = false;

        try {
            locked = lock.tryLock(5, 10 , TimeUnit.SECONDS);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        if(!locked) {
            throw new IllegalArgumentException("System busy, please try again later.");
        }

        try{
            CourseClass courseClass = courseClassRepository.findById(classId).orElseThrow(() -> new IllegalArgumentException("Class Id not found"));
            courseClass.registerStudent(studentId);
            courseClassRepository.save(courseClass);
        }finally {
            lock.unlock();
        }

    }

    @Transactional
    public void cancelRegistration(Long classId, Long studentId) {
        CourseClass courseClass = courseClassRepository.findById(classId).orElseThrow(() -> new IllegalArgumentException("Class Id not found"));

        courseClass.cancelRegistration(studentId);
        courseClassRepository.save(courseClass);
    }

}
