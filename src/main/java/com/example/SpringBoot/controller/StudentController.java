package com.example.SpringBoot.controller;

import com.example.SpringBoot.entity.OutputModel;
import com.example.SpringBoot.entity.Student;
import com.example.SpringBoot.service.StudentService;
import com.example.SpringBoot.service.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String getHome(String home) {
        return "welcome to E2Ehiring";
    }

    @PostMapping(value = "/student")
    public Student saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return student;
    }

    @GetMapping(value = "/allStudent")
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }

    @PutMapping(value = "/student")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }



    @DeleteMapping("/student/{rollNum}")
    public ResponseEntity<OutputModel> deleteStudent(@PathVariable("rollNum") Long rollNum) {
        OutputModel outputModel1 = new OutputModel();
        try {
            outputModel1 = (OutputModel) this.studentService.deleteByRollNum(rollNum);
            if (outputModel1.getErrorMessage().equals("")) {
                return new ResponseEntity<>(outputModel1, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(outputModel1, HttpStatus.NOT_FOUND);
              }
        return new ResponseEntity<>(outputModel1,HttpStatus.OK);
        }





    @GetMapping("/get{rollNum}")
    public ResponseEntity<OutputModel> getByRollNum(@PathVariable Long rollNum) {
        OutputModel outputModel = new OutputModel();
        try {
            outputModel = this.studentService.findByRollNum(rollNum);
            if (outputModel.getErrorMessage().equals("")) {
                return new ResponseEntity<>(outputModel, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(outputModel, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(outputModel, HttpStatus.valueOf(500));
        }


    }
}



