package com.example.studentservice.repository;

import com.example.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Katri Vidén
 * Date: 2021-12-21
 * Time: 13:43
 * Project: student-service
 * Copyright: MIT
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentId(Long studentId);

    @Transactional
    void deleteByStudentId(Long studentId);

}
