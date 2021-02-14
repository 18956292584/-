package com.springboot.Controller;

import com.springboot.Repository.UserService;
import com.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
     * 登录成功跳转主页面
     * @param user_id
     * @param user_password
     * @param model
     * @return
     */
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("user_id") String user_id,
                        @RequestParam("user_password") String user_password,
                        Model model,
                        HttpServletResponse response,
                        HttpServletRequest request){
        User user = userService.dealLogin(user_id, user_password);
        //创建session
        HttpSession session = request.getSession();
        if (user!=null) {
            //登录的用户信息保留三天
            String uuId = UUID.randomUUID().toString().replace("-", "");
            Cookie cookie = new Cookie("msg", uuId);
            cookie.setMaxAge(60 * 60 * 24 * 3);//保留用户信息时间
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);//发送到客户端
            return "index";//登录成功  进入主页面
        }
        model.addAttribute("msg","登录失败");
        return "login";//登录失败  重定向登录页面
    };




}
