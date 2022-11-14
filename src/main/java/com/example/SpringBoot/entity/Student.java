package com.example.SpringBoot.entity;

import javax.persistence.*;

@Entity
@Table(name="student")
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Column(unique = true)
    private Long rollNum;
    private String email;

    public Student() {

    }

    public Student(Long id, String name, Long rollNum, String email) {
        this.id = id;
        this.name = name;
        this.rollNum = rollNum;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRollNum() {
        return rollNum;
    }

    public void setRollNum(Long rollNum) {
        this.rollNum = rollNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    }
