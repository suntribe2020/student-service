package com.example.studentservice.service;

import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Katri Vidén
 * Date: 2022-01-04
 * Time: 14:53
 * Project: student-service
 * Copyright: MIT
 */

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldSaveStudentSuccessfully() {
        Student student = getTestStudent();

        when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student, studentService.save(student));
    }

    @Test
    void shouldGetAllStudentsSuccessfully() {
        List<Student> testStudents = getTestStudents();

        when(studentRepository.findAll()).thenReturn(testStudents);
        assertEquals(2, studentService.findAll().size());
    }

    @Test
    void shouldDeleteStudentByStudentId() {
        Student testStudent = getTestStudent();
        studentService.deleteByStudentId(testStudent.getStudentId());

        verify(studentRepository,times(1)).deleteByStudentId(testStudent.getStudentId());
    }

    private Student getTestStudent() {
        return new Student(1L,"ssn","firstName","lastName","address",
                "areaCode", "city", "email","courseId");
    }

    private List<Student> getTestStudents() {
        List<Student> allStudents = new ArrayList<>();
        allStudents.add(new Student(1L,"ssn","firstName","lastName","address",
                "areaCode", "city", "email","courseId"));
        allStudents.add(new Student(2L,"ssn2","firstName2","lastName2","address2",
                "areaCode2", "city2", "email2","courseId2"));
        return allStudents;
    }
}


