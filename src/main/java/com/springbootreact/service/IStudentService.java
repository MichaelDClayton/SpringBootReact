package com.springbootreact.service;

import com.springbootreact.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Student addStudent(Student student);
    List<Student> getStudents();
    Optional<Student> updateStudent(Student student, Long Id);
    Student getStudentById(Long Id);
    void deleteStudent(Long Id);
}
