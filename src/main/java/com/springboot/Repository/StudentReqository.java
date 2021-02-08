package com.springboot.Repository;

import com.springboot.entity.Student;

import java.util.List;

public interface StudentReqository {
    public Student getStudent(int id);
    public List<Student> findAll();
}
