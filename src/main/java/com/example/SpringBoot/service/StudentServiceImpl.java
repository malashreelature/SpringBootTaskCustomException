package com.example.SpringBoot.service;

import com.example.SpringBoot.entity.OutputModel;
import com.example.SpringBoot.service.exception.StudentAlreadyExistsException;
import com.example.SpringBoot.service.exception.StudentNotFoundException;
import com.example.SpringBoot.entity.Student;
import com.example.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }


    @Override
    public OutputModel findByRollNum(Long rollNum) {
        OutputModel model = new OutputModel();
        try {
            model.setStudent((Student) studentRepository.findByRollNum(rollNum).orElseThrow(() ->
                    new StudentNotFoundException("Student with rollNum not found")));
            model.setErrorMessage("");
            if (!model.getStudent().getRollNum().equals(rollNum)) {
                throw new StudentNotFoundException("");
            }
        } catch (StudentNotFoundException e) {
            model.setStudent(null);
            model.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            model.setStudent(null);
            model.setErrorMessage(e.getMessage());
        }
        return model;
    }

    @Override
    public OutputModel deleteByRollNum(Long rollNum) {
        OutputModel model1 = new OutputModel();
        try {
            var res = (Integer) this.studentRepository.deleteByRollNum(rollNum).orElseThrow(() ->
                    new StudentNotFoundException("Student with rollNum not found"));
            model1.setStudent(null);
            model1.setErrorMessage(" ");
            if (res != 1) {
                throw new StudentNotFoundException("Student with rollNum not found");
            }
        } catch (StudentNotFoundException e) {
            model1.setStudent(null);
            model1.setErrorMessage(e.getMessage());
        } catch (Exception e) {
            model1.setStudent(null);
            model1.setErrorMessage(e.getMessage());
        }
        return model1;

    }

    @Override
    public OutputModel save(Student student) throws StudentAlreadyExistsException{
        OutputModel model = new OutputModel();
        try {
            model.setStudent((Student) studentRepository.save(student));
            if (model!=null) {
                studentRepository.save(student);

            } else {
                throw new StudentAlreadyExistsException(
                        "Student already exists!!");}
        }
        catch (DataIntegrityViolationException e){
            throw new StudentAlreadyExistsException("Student already exists!!");
        }
        catch (Exception e) {
            model.setStudent(null);
            model.setErrorMessage(e.getMessage());
        }


        return model;
    }

}




