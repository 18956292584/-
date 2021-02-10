package com.springboot.Controller;

import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
//    @Autowired


    @RequestMapping("/")
    public ModelAndView index(){
//        System.out.println(123);
//        System.out.println(user.getUser("123").user_name);
        return new ModelAndView("index");
    }

    @RequestMapping("/load")
    public ModelAndView load(){
        return new ModelAndView("load");
    }

}
