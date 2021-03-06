package com.springboot.Controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookie = request.getCookies();//获取的是请求里的所有cookie组成的数组
        String user_id = null;
        for(int i = 0 ;cookie !=null && i < cookie.length; i++){
            if("user_id".equals(cookie[i].getName())){
                user_id = cookie[i].getValue();//得到peter
                break;
            }
        }
        HttpSession session = request.getSession();
        if(user_id != null &&  session.getAttribute(user_id) != null){
//          request.getRequestDispatcher("").forward(request, response);
            return true;
        }else {
            request.setAttribute("msg", "无权限请先登录");
            response.sendRedirect("/user/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
