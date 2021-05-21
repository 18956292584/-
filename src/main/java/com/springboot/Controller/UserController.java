package com.springboot.Controller;

import com.alipay.api.AlipayApiException;
import com.springboot.Repository.Impl.PayAction;
import com.springboot.Repository.Impl.Util;
import com.springboot.Repository.IndexService;
import com.springboot.Repository.OrderService;
import com.springboot.Repository.UserService;
import com.springboot.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    IndexService indexService;
    @Autowired
    OrderService orderService;
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
            request.getRequestDispatcher("login?res=" +  URLEncoder.encode(user_login,"utf-8")).forward(request, response);
        }
    }



    //购物车
    //访问地址： localhost:9090/user/cart
    @RequestMapping("/cart")
    public ModelAndView cart(HttpServletResponse response,
                             HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("cart");
        String userId = Util.getUserId(request);
        if(userId == null){
            throw new Exception("用户没有登录");
        }
        //购物车所有商品
        modelAndView.addObject("map",indexService.getGwc(userId));
        modelAndView.addObject("userId",userId);

        return modelAndView;
    }

    //确认订单
    //访问地址： http://localhost:9090/user/confirm_order?userId=123&gwcId=3AND
    @RequestMapping("/confirm_order")
    public ModelAndView confirm_order(String userId,String gwcId){
        ModelAndView modelAndView = new ModelAndView("confirmOrder");
        OrderModel orderModel = orderService.dealConfirmOrder(userId,gwcId);
        modelAndView.addObject("orderModel",orderModel);
        modelAndView.addObject("address",userService.getAddress(userId));
        modelAndView.addObject("gwcId",gwcId);

        return modelAndView;
    }

    @RequestMapping(value = "/addAddress")
    public void addAddress(Address address,HttpServletResponse response,HttpServletRequest request) throws IOException, ServletException {
        userService.addAddress(address);
        response.sendRedirect("confirm_order?userId=" +  URLEncoder.encode(address.getA_user(),"utf-8") + "&gwcId=" + address.getA_gwc());
    }

    //添加新物品至购物车
    @RequestMapping("/addGwc1")
    public void addGwc1(Gwc gwc, HttpServletResponse response,
                       HttpServletRequest request) throws IOException {
        userService.addGwc(gwc);
        response.sendRedirect("/user/cart?userId=" + gwc.getCust_id());
    }

    //添加新物品至购物车
    @RequestMapping("/addGwc")
    public void addGwc(Gwc gwc, HttpServletResponse response,
                       HttpServletRequest request) throws IOException {
        userService.addGwc(gwc);
    }

    //用户订单中心
    //访问地址： localhost:9090/index/user_center?userId=123
    @RequestMapping("/user_center")
    public ModelAndView user_center(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = Util.getUserId(request);
        if(userId == null){
            throw new Exception("用户没有登录");
        }

        ModelAndView modelAndView = new ModelAndView("user_center");
        modelAndView.addObject("dfk",orderService.dfk(userId,0));
        modelAndView.addObject("dfh",orderService.dfk(userId,1));
        modelAndView.addObject("dsh",orderService.dfk(userId,2));
        modelAndView.addObject("dpj",orderService.dfk(userId,3));

        return modelAndView;
    }

    //用户订单详细
    //访问地址： localhost:9090/user/user_order?userId=123
    @RequestMapping("/user_order")
    public ModelAndView request(String order_id,int order_state){
        ModelAndView modelAndView = new ModelAndView("user_order");
        modelAndView.addObject("orderfoods",orderService.getorderModel(order_id));
        modelAndView.addObject("order_state",order_state);
        return modelAndView;
    }

    ////更改购物车数量
    @RequestMapping("/dealGwcAdd")
    @ResponseBody
    public String dealGwcAdd(String custid,int foodid, int count){
        userService.updateNum(custid,foodid,count);
        return "succese";
    }

    //删除购物车
    @RequestMapping("/removeGwc")
    @ResponseBody
    public String removeGwc(int custid,int foodid){
        userService.removeGwc(custid,foodid);
        return "succese";
    }

    //提交订单
    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/submitOrder")
    @ResponseBody
    public String submit(Order order){
        String res = orderService.submitOrder(order);
        return res;
    }

    //用户地址
    //访问地址： localhost:9090/user/user_address
    @RequestMapping("/user_address")
    public ModelAndView user_address(HttpServletResponse response,
                                     HttpServletRequest request) throws Exception {
        String userId = Util.getUserId(request);
        if(userId == null){
            throw new Exception("用户没有登录");
        }
        ModelAndView modelAndView = new ModelAndView("user_address");
        modelAndView.addObject("address",userService.getAddress(userId));
        modelAndView.addObject("user",userId);

        return modelAndView;
    }

    //用户地址
    //访问地址： localhost:9090/user/addAddress1
    @RequestMapping("/addAddress1")
    public void addAddress1(HttpServletResponse response,Address address,HttpServletRequest request) throws Exception {
        userService.addAddress(address);
        response.sendRedirect("/user/user_address");
    }

    //用户地址
    //访问地址： localhost:9090/user/ChangeAddress
    @RequestMapping("/ChangeAddress")
    public void ChangeAddress(Address address,HttpServletResponse response,HttpServletRequest request) throws Exception {
        userService.ChangeAddress(address);
        response.sendRedirect("/user/user_address");
    }

    //用户地址
    //访问地址： localhost:9090/user/DeletAddress
    @RequestMapping("/DeletAddress")
    public void DeletAddress(Address address,HttpServletResponse response,HttpServletRequest request) throws Exception {
        userService.DeletAddress(address);
        response.sendRedirect("/user/user_address");
    }

    //用户账户
    //访问地址： localhost:9090/user/user_account
    @RequestMapping("/user_account")
    public ModelAndView user_account(HttpServletResponse response,
                                     HttpServletRequest request) throws Exception {
        String userId = Util.getUserId(request);
        if(userId == null){
            throw new Exception("用户没有登录");
        }
        ModelAndView modelAndView = new ModelAndView("user_account");
        modelAndView.addObject("user",userService.getUser(userId));

        return modelAndView;
    }

    //用户地址
    //访问地址： localhost:9090/user/ChangeUserInfo
    @RequestMapping("/ChangeUserInfo")
    public void ChangeUserInfo(User user,HttpServletResponse response,
                               HttpServletRequest request) throws Exception {
        userService.changeUserInfo(user);
        response.sendRedirect("/user/user_account");
    }

    //用户地址
    //访问地址： localhost:9090/user/ChangeUserInfo
    @RequestMapping("/changeAddress3")
    public void changeAddress3(Address address,HttpServletResponse response,
                               HttpServletRequest request) throws Exception {
        userService.ChangeAddress(address);
        response.sendRedirect("/user/user_center");
    }

    //订单支付
    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/respond")
    public ModelAndView respond(String orderid,double order_price){
        ModelAndView modelAndView = new ModelAndView("respond");
        String res = null;
        try {
            res = PayAction.payAction(order_price,"校园外卖",orderid);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("res",res);
        return modelAndView;
    }


    //查询是否支付成功
    @RequestMapping("/request")
    public void request(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String res = orderService.ckeckOrder(request,response);
        response.sendRedirect("user_center?userId=" + res);
    }


    //删除订单
    @RequestMapping("/deleteOrder")
    public void deleteOrder(String order_id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        orderService.deleteOrder(order_id);
        response.sendRedirect("/user/user_center?");
    }


    //用户评价
    @RequestMapping("/user_pj")
    public ModelAndView user_pj(String order_id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("user_pj");
        modelAndView.addObject("orderfoods",orderService.getorderModel(order_id));
        modelAndView.addObject("order_state",3);
        modelAndView.addObject("order_id",order_id);
        return modelAndView;
    }


    @RequestMapping("/pjFood")
    public void user_pj(@RequestParam(value = "e_food_id")ArrayList<Integer> e_food_id,
                        @RequestParam(value = "e_user_id")ArrayList<String> e_user_id,
                        @RequestParam(value = "e_order_id")ArrayList<String> e_order_id,
                        @RequestParam(value = "e_b_id")ArrayList<String> e_b_id,
                        @RequestParam(value = "order_pf")double order_pf,
                        @RequestParam(value = "e_evaluate")ArrayList<String> e_evaluate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(e_order_id.get(0));
        userService.dealPJ(e_food_id,e_evaluate,e_user_id,e_order_id,e_b_id,order_pf);
        response.sendRedirect("/user/user_center?");
    }

    //删除订单
    @RequestMapping("/confirmReceipt")
    public void confirmReceipt(String order_id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        orderService.confirmReceipt(order_id);
        response.sendRedirect("/user/user_center?");
    }

    //安全退出
    @RequestMapping("/Offline")
    public void Offline(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = Util.getUserId(request);
        if(userId != null) {
            if (request.getSession().getAttribute(userId) != null) {
                request.getSession().removeAttribute(userId);
            }
        }

        response.sendRedirect("/index/");
    }




}
