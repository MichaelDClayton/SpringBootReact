package com.springbootreact.service;

import com.springbootreact.exception.StudentAlreadyExistException;
import com.springbootreact.exception.StudentNotFoundException;
import com.springbootreact.model.Student;
import com.springbootreact.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExist(student.getEmail())){
            throw new StudentAlreadyExistException(student.getEmail() + " This student already exists");
        }
        studentRepository.save(student);

        return student;
    }

    private boolean studentAlreadyExist(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> updateStudent(Student student, Long Id) {
        return Optional.ofNullable(studentRepository.findById(Id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            studentRepository.save(st);
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found")));
    }

    @Override
    public Student getStudentById(Long Id) {
        return studentRepository.findById(Id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, student with this Id: "+ Id +" could not be found"));
    }

    @Override
    public void deleteStudent(Long Id) {
        if(!studentRepository.existsById(Id)){
            throw new StudentNotFoundException("Sorry, student with this Id: "+ Id +" could not be found");
        }
        studentRepository.deleteById(Id);
    }
}
