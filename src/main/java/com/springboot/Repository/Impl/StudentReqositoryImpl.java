package com.springboot.Repository.Impl;

import com.springboot.Repository.StudentReqository;
import com.springboot.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class StudentReqositoryImpl implements StudentReqository {
    private static Map<Integer,Student> map = new HashMap<Integer, Student>();

    static {
        map.put(1,new Student(1,"张三"));
        map.put(2,new Student(2,"李四"));
        map.put(3,new Student(3,"王五"));
    }


    public Student getStudent(int id) {
        return map.get(id);
    }

    public List<Student> findAll() {
        Collection<Student> values = map.values();
        return new ArrayList<Student>(values);
    }
}
