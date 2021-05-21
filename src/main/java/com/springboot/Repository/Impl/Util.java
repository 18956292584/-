package com.springboot.Repository.Impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {
    public  static  String getUserId(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        String user_id = null;
        for(int i = 0; cookie != null && i < cookie.length; i++){
            if("user_id".equals(cookie[i].getName())){
                user_id = cookie[i].getValue();
            }
        }
        if(user_id!=null){
            return user_id;
        } else{
            return null;
        }
    }

    public  static  String getBusId(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        String user_id = null;
        for(int i = 0; cookie != null && i < cookie.length; i++){
            if("BUS".equals(cookie[i].getName())){
                user_id = cookie[i].getValue();
            }
        }
        if(user_id!=null){
            return user_id.replace("BUS","");
        } else{
            return null;
        }
    }
}
