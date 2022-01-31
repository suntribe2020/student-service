package com.example.studentservice.controller;

import com.example.studentservice.VO.Course;
import com.example.studentservice.VO.ResponseTemplateVO;
import com.example.studentservice.entity.Student;
import com.example.studentservice.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Katri Vidén
 * Date: 2022-01-04
 * Time: 16:49
 * Project: student-service
 * Copyright: MIT
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    //For serialization purposes
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Long ID = 1L;
    private static final Long STUDENT_ID = 1L;
    private static final String SSN = "ssn";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String AREA_CODE = "areaCode";
    private static final String CITY = "city";
    private static final String EMAIL = "email";
    private static final String COURSE_ID = "courseId";

    @Test
    void saveStudentTest() throws Exception {
        Student testStudent = getTestStudent();

        when(studentService.save(any(Student.class))).thenReturn(testStudent);

        mockMvc.perform(post("/student/save")
                .content(objectMapper.writeValueAsString(testStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(testStudent.getStudentId()))
                .andExpect(jsonPath("$.ssn").value(testStudent.getSsn()))
                .andExpect(jsonPath("$.firstName").value(testStudent.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testStudent.getLastName()))
                .andExpect(jsonPath("$.address").value(testStudent.getAddress()))
                .andExpect(jsonPath("$.areaCode").value(testStudent.getAreaCode()))
                .andExpect(jsonPath("$.city").value(testStudent.getCity()))
                .andExpect(jsonPath("$.email").value(testStudent.getEmail()))
                .andExpect(jsonPath("$.courseId").value(testStudent.getCourseId()));
    }

    @Test
    void updateStudentTest() throws Exception {
        Student updatedStudent = getUpdatedStudent();

        when(studentService.updateStudent(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(patch("/student/updateStudent")
                .content(objectMapper.writeValueAsString(updatedStudent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(STUDENT_ID))
                .andExpect(jsonPath("$.ssn").value(updatedStudent.getSsn()))
                .andExpect(jsonPath("$.firstName").value(updatedStudent.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedStudent.getLastName()))
                .andExpect(jsonPath("$.address").value(updatedStudent.getAddress()))
                .andExpect(jsonPath("$.areaCode").value(updatedStudent.getAreaCode()))
                .andExpect(jsonPath("$.city").value(updatedStudent.getCity()))
                .andExpect(jsonPath("$.email").value(updatedStudent.getEmail()))
                .andExpect(jsonPath("$.courseId").value(updatedStudent.getCourseId()));
    }

    @Test
    void getStudentByStudentIdTest() throws Exception {
        Student testStudent = getTestStudent();

        when(studentService.findByStudentId(testStudent.getStudentId())).thenReturn(testStudent);

        mockMvc.perform(get("/student/findByStudentId/" + testStudent.getStudentId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(testStudent.getStudentId()))
                .andExpect(jsonPath("$.ssn").value(testStudent.getSsn()))
                .andExpect(jsonPath("$.firstName").value(testStudent.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testStudent.getLastName()))
                .andExpect(jsonPath("$.address").value(testStudent.getAddress()))
                .andExpect(jsonPath("$.areaCode").value(testStudent.getAreaCode()))
                .andExpect(jsonPath("$.city").value(testStudent.getCity()))
                .andExpect(jsonPath("$.email").value(testStudent.getEmail()))
                .andExpect(jsonPath("$.courseId").value(testStudent.getCourseId()));
    }

    @Test
    void getAllStudentsTest() throws Exception {
        Student testStudent = getTestStudent();
        List<Student> allStudents = List.of(testStudent);

        when(studentService.findAll()).thenReturn(allStudents);

        mockMvc.perform(get("/student/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getStudentWithCourseTest() throws Exception {
        Student testStudent = getTestStudent();
        Course testCourse = getTestCourse();
        List<Course> courseList = List.of(testCourse);

        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();

        when(studentService.findByStudentId(testStudent.getStudentId())).thenReturn(testStudent);
        when(studentService.getStudentWithCourse(testStudent.getStudentId())).thenReturn(responseTemplateVO);

        responseTemplateVO.setStudent(testStudent);
        responseTemplateVO.setCourse(courseList);

        mockMvc.perform(get("/student/getStudentWithCourse/" + testStudent.getStudentId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStudentByIdTest() throws Exception {
        Student testStudent = getTestStudent();

        when(studentService.deleteByStudentId(testStudent.getStudentId())).thenReturn(String.valueOf(testStudent));

        mockMvc.perform(delete("/student/deleteByStudentId/" + testStudent.getStudentId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Student getTestStudent() {
        Student testStudent = new Student();
        testStudent.setStudentId(STUDENT_ID);
        testStudent.setSsn(SSN);
        testStudent.setFirstName(FIRST_NAME);
        testStudent.setLastName(LAST_NAME);
        testStudent.setAddress(ADDRESS);
        testStudent.setAreaCode(AREA_CODE);
        testStudent.setCity(CITY);
        testStudent.setEmail(EMAIL);
        testStudent.setCourseId(COURSE_ID);

        return testStudent;
    }

    private Student getUpdatedStudent() {
        Student updatedStudent = getTestStudent();
        updatedStudent.setSsn("updatedSsn");
        updatedStudent.setFirstName("updatedFirstName");
        updatedStudent.setLastName("updatedLastName");
        updatedStudent.setAddress("updatedAddress");
        updatedStudent.setAreaCode("updatedAreaCode");
        updatedStudent.setCity("updatedCity");
        updatedStudent.setEmail("updatedEmail");
        updatedStudent.setCourseId("updatedCourseId");

        return updatedStudent;
    }

    private Course getTestCourse() {
        Course testCourse = new Course();
        testCourse.setId(ID);
        testCourse.setCourseTitle("courseTitle");
        testCourse.setCourseId("courseId");
        testCourse.setDuration("duration");

        return testCourse;
    }
}

