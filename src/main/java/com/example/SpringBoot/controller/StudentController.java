package com.example.SpringBoot.controller;

import com.example.SpringBoot.entity.OutputModel;
import com.example.SpringBoot.entity.Student;
import com.example.SpringBoot.service.StudentService;
import com.example.SpringBoot.service.exception.StudentAlreadyExistsException;
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


    @GetMapping(value = "/allStudent")
    public List<Student> findAllStudent() {
        return studentService.findAllStudent();
    }


    @PostMapping(value = "/student")
    public ResponseEntity<OutputModel> CreateStudent(@RequestBody  Student student) {
        OutputModel model = new OutputModel();
        try {
           if(model!=null){
               model=(OutputModel) studentService.save(student);
               return new ResponseEntity<>(model, HttpStatus.CREATED);
           }
           else{
               return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
            }
        }catch (StudentAlreadyExistsException e){
            model.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(model, HttpStatus.valueOf(500));
        }

    }




    @DeleteMapping("/student/{rollNum}")
    public ResponseEntity<OutputModel> deleteStudent(@PathVariable("rollNum") Long rollNum) {
        OutputModel outputModel1 = new OutputModel();
        try {
            outputModel1 = (OutputModel) this.studentService.deleteByRollNum(rollNum);
            if (outputModel1.getErrorMessage().equals("")) {
                return new ResponseEntity<>(outputModel1, HttpStatus.OK);

            }else{
                return new ResponseEntity<>(outputModel1,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(outputModel1, HttpStatus.valueOf(500));
              }

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



