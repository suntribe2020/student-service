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

    private static final Long ID = 1L;

    @Test
    void shouldSaveStudentSuccessfully() {
        Student testStudent = getTestStudent();

        when(studentRepository.save(testStudent)).thenReturn(testStudent);
        assertEquals(testStudent, studentService.save(testStudent));
    }

    @Test
    void shouldUpdateStudentSuccessfully() {
        Student testStudent = getTestStudent();

        when(studentRepository.findByStudentId(ID)).thenReturn(testStudent);
        when(studentService.findAll()).thenReturn(List.of(testStudent));

        Student updatedStudent = new Student();
        updatedStudent.setStudentId(ID);
        updatedStudent.setSsn("updatedSsn");
        updatedStudent.setFirstName("updatedFirstName");
        updatedStudent.setLastName("updatedLastname");
        updatedStudent.setAddress("updatedAddress");
        updatedStudent.setAreaCode("updatedAreaCode");
        updatedStudent.setCity("updatedCity");
        updatedStudent.setEmail("updatedEmail");
        updatedStudent.setCourseId("updatedCourseId");

        studentService.updateStudent(updatedStudent);

        List<Student> actual = studentService.findAll();
        Student actualStudent = actual.get(0);

        assertEquals(ID, actualStudent.getStudentId());
        assertEquals(testStudent.getSsn(), actualStudent.getSsn());
        assertEquals(testStudent.getFirstName(), actualStudent.getFirstName());
        assertEquals(testStudent.getLastName(), actualStudent.getLastName());
        assertEquals(testStudent.getAddress(), actualStudent.getAddress());
        assertEquals(testStudent.getAreaCode(), actualStudent.getAreaCode());
        assertEquals(testStudent.getCity(), actualStudent.getCity());
        assertEquals(testStudent.getEmail(), actualStudent.getEmail());
        assertEquals(testStudent.getCourseId(), actualStudent.getCourseId());
    }

    @Test
    void shouldFindStudentByStudentId() {
        Student testStudent = getTestStudent();

        when(studentRepository.findByStudentId(ID)).thenReturn(testStudent);
        Student actualStudent = studentService.findByStudentId(ID);

        verify(studentRepository).findByStudentId(ID);
        assertEquals(studentRepository.findByStudentId(ID), actualStudent);
    }

    @Test
    void shouldGetAllStudentsSuccessfully() {
        List<Student> allTestStudents = new ArrayList<>();
        allTestStudents.add(new Student(1L,"700327-3882","Tyrion","Lannister",
                "Casterly Rock","12879", "Västeros", "halfman@mail.com","2"));

        allTestStudents.add(new Student(2L,"750812-4739","Daenerys","Targaryen",
                "Thrones 3","32783", "Västeros", "khaleesi@mail.com","1"));

        when(studentRepository.findAll()).thenReturn(allTestStudents);
        assertEquals(2, studentService.findAll().size());
    }

    @Test
    void shouldDeleteStudentByStudentId() {
        Student testStudent = getTestStudent();

        when(studentRepository.save(testStudent)).thenReturn(testStudent);

        Student actualStudent = studentService.save(testStudent);
        assertEquals(testStudent.getStudentId(), actualStudent.getStudentId());

        studentService.deleteByStudentId(ID);
        verify(studentRepository,times(1)).deleteByStudentId(testStudent.getStudentId());
    }

    private Student getTestStudent() {
        Student student = new Student();
        student.setStudentId(ID);
        student.setSsn("ssn");
        student.setFirstName("firstName");
        student.setLastName("lastName");
        student.setAddress("address");
        student.setAreaCode("areaCode");
        student.setCity("city");
        student.setEmail("email");
        student.setCourseId("courseId");

        return student;
    }
}


