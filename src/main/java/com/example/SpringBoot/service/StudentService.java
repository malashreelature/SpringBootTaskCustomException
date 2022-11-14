package com.example.SpringBoot.service;

import com.example.SpringBoot.entity.OutputModel;
import com.example.SpringBoot.entity.Student;
import com.example.SpringBoot.service.exception.StudentAlreadyExistsException;

import java.util.List;

public interface StudentService {

    public List<Student> findAllStudent();

    OutputModel findByRollNum(Long rollNum);

    OutputModel deleteByRollNum(Long rollNum);

    Object save(Student student) throws StudentAlreadyExistsException;


}
