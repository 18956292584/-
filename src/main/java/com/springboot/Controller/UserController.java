package com.springboot.Controller;

import com.springboot.Repository.UserService;
import com.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerGet(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@ModelAttribute(value = "user") User user,
                         Model model,
                         HttpServletResponse response,
                         HttpServletRequest request) {
        //注册用户
        String result = userService.registerUser(user);
        //将结果放入model中，在模板可以取道model的值
        model.addAttribute("result",result);
        return response.encodeRedirectURL("/index");
    }


    //方法用户跳转登录页面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Model model,
                        @ModelAttribute(value = "user") User user,
                        HttpServletResponse response,
                        HttpSession session
                        ){
        String user_login = userService.dealLogin(user);
        //登录成功，将用户存入cookie和session
        if (user_login.equals("登录成功")){
            String user_uuid = UUID.randomUUID().toString().replace("-", "");
            Cookie cookie = new Cookie(user_uuid, user.getUser_id());
            //cookie有效期三天
            cookie.setMaxAge(60*60*24*3);
            //发送到客户端
            response.addCookie(cookie);
            //保存用户到session
            session.setAttribute("user",user);
            return response.encodeRedirectURL("/index");
        }
        //密码错误重写登录 并提示
        model.addAttribute("user_login",user_login);
        return response.encodeRedirectURL("/login");
    }



}
