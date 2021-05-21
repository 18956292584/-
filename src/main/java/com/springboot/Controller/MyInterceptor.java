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

        if(request.getRequestURI().contains("business")){
            String BUS_ID = null;
            for(int i = 0 ;cookie !=null && i < cookie.length; i++){
                if("BUS".equals(cookie[i].getName())){
                    BUS_ID = cookie[i].getValue();//得到peter
                    break;
                }
            }
            HttpSession session = request.getSession();
            if(BUS_ID != null &&  session.getAttribute(BUS_ID) != null){
                return true;
            }else {
                System.out.println(BUS_ID);
                System.out.println(session.getAttribute( "BUS"+ BUS_ID));
                request.setAttribute("msg", "无权限请先登录");
                response.sendRedirect("/business/BusLoad");
                return false;
            }

        } else {
            String user_id = null;
            for(int i = 0 ;cookie !=null && i < cookie.length; i++){
                if("user_id".equals(cookie[i].getName())){
                    user_id = cookie[i].getValue();//得到peter
                    break;
                }
            }
            HttpSession session = request.getSession();
            if(user_id != null &&  session.getAttribute(user_id) != null){
                return true;
            }else {
                request.setAttribute("msg", "无权限请先登录");
                response.sendRedirect("/user/login");
                return false;
            }

        }



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
