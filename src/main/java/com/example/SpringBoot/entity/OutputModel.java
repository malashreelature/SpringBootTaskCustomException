package com.example.SpringBoot.entity;


public class OutputModel {

    private String errorMessage;
    private Student student;

    public Student getStudent() {
        return student;
    }

    public OutputModel() {

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public OutputModel(String errorMessage, Student student) {
        this.errorMessage = errorMessage;
        this.student = student;
    }
}