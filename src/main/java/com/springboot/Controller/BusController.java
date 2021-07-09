package com.springboot.Controller;

import com.springboot.Repository.BusService;
import com.springboot.Repository.Impl.Util;
import com.springboot.Repository.OrderService;
import com.springboot.entity.Food;
import com.springboot.entity.IdPasswordModel;
import com.springboot.mapper.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("business")
public class BusController {

    @Autowired
    BusService busService;
    @Autowired
    OrderService orderService;


    //访问地址： localhost:9090/business/BusLoad
    @RequestMapping("/BusLoad")
    public ModelAndView BusLoad(String res, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("BusLoad");
        modelAndView.addObject("res",res);
        return modelAndView;
    }

    //访问地址： localhost:9090/business/BusLoad
    @RequestMapping("/Load")
    public void Load(IdPasswordModel idPasswordModel,
                              HttpServletResponse response,
                             HttpServletRequest request,
                             HttpSession session) throws ServletException, IOException {
        //System.out.println(user);
        String user_login = busService.dealLogin(idPasswordModel);
        //登录成功，将用户存入cookie和session
        if (user_login.equals("登录成功")){
            Cookie cookie = new Cookie("BUS", "BUS" + idPasswordModel.getId());
            //cookie有效期三天
            cookie.setMaxAge(60*60*3);
            cookie.setPath("/");
            //发送到客户端
            response.addCookie(cookie);
            //保存用户到session

            session.setAttribute("BUS" + idPasswordModel.getId(),idPasswordModel);
            session.setMaxInactiveInterval(60*60*3);
            response.sendRedirect("/business/ProductManagement?state=1");
        } else {
            response.sendRedirect("/business/BusLoad?res=" + URLEncoder.encode(user_login,"utf-8"));
        }

    }

    @RequestMapping("/ProductManagement")
    public ModelAndView ProductManagement(int state,HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("ProductManagement");
        String bus = Util.getBusId(request);
        if(bus==null){
            throw new Exception("请重新登录");
        }

        model.addObject("allfood",busService.getAllFood(bus,state));
        model.addObject("b_id",bus);
        model.addObject("bus",busService.getBusById(bus));
        return model;
    }

    //访问地址： localhost:9090/business/Bus_center
    @RequestMapping("/Bus_center")
    public ModelAndView Bus_center(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("Bus_center");
        String bus = Util.getBusId(request);
        if(bus==null){
            throw new Exception("请重新登录");
        }

        modelAndView.addObject("dfh",orderService.dfkBus(bus,1));
        modelAndView.addObject("dsh",orderService.dfkBus(bus,2));
        modelAndView.addObject("dpj",orderService.dfkBus(bus,3));
        modelAndView.addObject("bus",busService.getBusById(bus));

        return modelAndView;
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, String>  upload(MultipartFile photo, HttpServletRequest request){
        Map<String, String> ret = new HashMap<String, String>();
        if (photo == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (photo.getSize() > 1024 * 1024 * 10) {
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1, photo.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/

        String savePath ="F:\\毕设\\新建文件夹\\-\\src\\main\\resources\\static\\upload\\";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            System.out.println(savePath + filename);
            photo.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "上传图片成功！");
        ret.put("filepath", request.getSession().getServletContext().getContextPath() + "/static/images/upload/");
        ret.put("filename", filename);
        return ret;
    }

    //访问地址： localhost:9090/business/newfood
    @RequestMapping("/newfood")
    public void newfood(Food food, HttpServletResponse response,
                        HttpServletRequest request) throws IOException {
        busService.addFood(food);

        response.sendRedirect("/business/ProductManagement?state=1");
    }

    //访问地址： localhost:9090/business/changeFood
    @RequestMapping("/changeFood")
    public void changeFood(Food food, HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        busService.changeFood(food);

        response.sendRedirect("/business/ProductManagement?state=1");
    }

    //访问地址： localhost:9090/business/foodState
    @RequestMapping("/foodState")
    public void foodState(int state,int foodid, HttpServletResponse response,
                        HttpServletRequest request) throws IOException {
        busService.foodState(foodid,state);

        response.sendRedirect("/business/ProductManagement?state=1");
    }


    @RequestMapping("/searchFood")
    public ModelAndView searchFood(String foodName,String busId){
        ModelAndView model = new ModelAndView("ProductManagement");
        model.addObject("allfood",busService.searchFood(foodName,busId));
        model.addObject("b_id",busId);

        return model;
    }

    //用户订单详细
    //访问地址： localhost:9090/business/user_order?
    @RequestMapping("/user_order")
    public ModelAndView request(String order_id,int order_state){
        ModelAndView modelAndView = new ModelAndView("bus_Order");
        modelAndView.addObject("orderfoods",orderService.getorderModel(order_id));
        modelAndView.addObject("order_state",order_state);
        return modelAndView;
    }


    //访问地址： localhost:9090/business/order_fh
    @RequestMapping("/order_fh")
    public void order_fh(String order_id,HttpServletResponse response,
                          HttpServletRequest request) throws IOException {
        orderService.order_fh(order_id);

        response.sendRedirect("/business/Bus_center");
    }

    //用户订单详细
    //访问地址： localhost:9090/business/bus_Account
    @RequestMapping("/bus_Account")
    public ModelAndView bus_Account(HttpServletResponse response,
                                    HttpServletRequest request) throws Exception {
        String bus = Util.getBusId(request);
        if(bus==null){
            throw new Exception("请重新登录");
        }
        ModelAndView modelAndView = new ModelAndView("bus_Account");
        modelAndView.addObject("bus",busService.getBusById(bus));
        return modelAndView;
    }

    //安全退出
    @RequestMapping("/Offline")
    public void Offline(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = Util.getBusId(request);
        if(userId != null) {
            if (request.getSession().getAttribute("BUS"+userId) != null) {
                request.getSession().removeAttribute("BUS"+userId);
            }
        }
        response.sendRedirect("/index/");
    }

}
