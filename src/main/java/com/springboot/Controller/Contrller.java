package com.springboot.Controller;

import com.springboot.Repository.StudentReqository;
import com.springboot.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class Contrller {
//    @Autowired
//    private StudentReqository dao;

    @RequestMapping("/")
    public ModelAndView index(){
        System.out.println(123);
        return new ModelAndView("index");
    }

    @RequestMapping("/load")
    public ModelAndView load(){
        return new ModelAndView("load");
    }

//    @GetMapping("/findAll")
//    public List<Student> findAll(){
//        return  dao.findAll();
//    }
//
//    @GetMapping("/findById/{id}")
//    public Student findById(@PathVariable("id")int id){
//        System.out.println(id);
//        return  dao.getStudent(id);
//    }

}
