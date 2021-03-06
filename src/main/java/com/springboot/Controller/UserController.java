package com.springboot.Controller;

import com.springboot.Repository.UserService;
import com.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 方法用于跳转注册页面
     * @return
     */

    @RequestMapping(value = "/register")
    public ModelAndView registerGet(String res){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("res",res);
        return modelAndView;
    }

    @RequestMapping(value = "/registerUser")
    public void registerUser(@ModelAttribute(value = "user") User user,
                         HttpServletResponse response) throws IOException {
        //注册用户
        String result = userService.registerUser(user);
        if(result!="注册成功"){
            response.sendRedirect("register？res=" + URLEncoder.encode(result,"utf-8"));
        } else {
            response.sendRedirect("login？res=" + URLEncoder.encode(result,"utf-8"));
        }
    }


    //方法用户跳转登录页面
    @RequestMapping(value = "/login")
    public ModelAndView login(String res, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("res",res);
        return modelAndView;
    }

    @RequestMapping(value = "/loginGet")
    public void loginGet(@ModelAttribute(value = "user") User user,
                              HttpServletResponse response,
                              HttpServletRequest request,
                              HttpSession session
                        ) throws IOException, ServletException {

        //System.out.println(user);
        String user_login = userService.dealLogin(user);
        //登录成功，将用户存入cookie和session
        if (user_login.equals("登录成功")){
            String user_uuid = UUID.randomUUID().toString().replace("-", "");

            Cookie cookie = new Cookie("user_id", user.getUser_id());
            //cookie有效期三天
            cookie.setMaxAge(60*60*3);
            cookie.setPath("/");
            //发送到客户端
            response.addCookie(cookie);
            //保存用户到session

            session.setAttribute(user.getUser_id(),user);
            session.setMaxInactiveInterval(60*60*3);
            request.getRequestDispatcher("../index/").forward(request, response);
        } else {
            request.getRequestDispatcher("login?res=" +  URLEncoder.encode(user_login,"utf-8")).forward(request, response);;
        }
    }



}
