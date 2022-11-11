package com.example.SpringBoot.service;

import com.example.SpringBoot.entity.OutputModel;
import com.example.SpringBoot.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);

    public Student updateStudent(Student student);

    public List<Student> findAllStudent();

    OutputModel findByRollNum(Long rollNum);

    OutputModel deleteByRollNum(Long rollNum);


}
