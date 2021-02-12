package com.springboot.Controller;

import com.springboot.Repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/load")
    @ResponseBody
    public String login(String id,String password){
        //todo
        System.out.println(123);
        return userService.dealLogin(id,password);
    };




}
