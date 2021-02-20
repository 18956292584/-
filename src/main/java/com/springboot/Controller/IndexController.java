package com.springboot.Controller;

import com.alipay.api.AlipayApiException;
import com.springboot.Repository.Impl.PayAction;
import com.springboot.Repository.IndexService;
import com.springboot.Repository.UserService;
import com.springboot.entity.Gwc;
import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    UserService userService;

    //访问地址： localhost:9090/index/
    @RequestMapping("/")
    public ModelAndView index(){

        ModelAndView model = new ModelAndView("index");
        //所有食物
        model.addObject("allfood",indexService.allFood());
        //所有商家
        model.addObject("allbusiness",indexService.allbusiness());
        //售量前三
        model.addObject("top3food",indexService.top3fod());
        //最新10条评价
        model.addObject("top10evaluate",indexService.top10evaluate());
        return model;
    }

    //美食页面
    //访问地址： localhost:9090/index/category
    @RequestMapping("/category")
    public ModelAndView category(){
        ModelAndView modelAndView = new ModelAndView("category");
        //购物车所有商品
        modelAndView.addObject("allfood",indexService.allFood());
        modelAndView.addObject("othershop",indexService.otherShop());
        return modelAndView;
    }


    //商家页面
    //访问地址： localhost:9090/index/shop?shopid=1
    @RequestMapping("/shop")
    public ModelAndView shop(int shopid){
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("shop",indexService.getshop(shopid));
        modelAndView.addObject("shopfood",indexService.shopfood(shopid));
        modelAndView.addObject("othershop",indexService.othershop(shopid));
        modelAndView.addObject("evaluates",indexService.evaluateByshop(shopid));

        return modelAndView;
    }

    //访问地址： localhost:9090/index/detailsp?foodid=1
    @RequestMapping("/detailsp")
    public ModelAndView detailsp(int foodid){
        ModelAndView modelAndView = new ModelAndView("detailsp");
        System.out.println(indexService.getFood(foodid).getB_name() + "------------------------");
        modelAndView.addObject("food",indexService.getFood(foodid));
        modelAndView.addObject("top3food",indexService.top3food());
        modelAndView.addObject("foodEvaluate",indexService.evaluateByshop(foodid));
        modelAndView.addObject("userid",123);

        return modelAndView;
    }

    //访问地址： localhost:9090/index/list
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("list");
        //所有商家
        modelAndView.addObject("allbusiness",indexService.allbusiness());

        return modelAndView;
    }

    //购物车
    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/cart")
    public ModelAndView cart(int userId){
        ModelAndView modelAndView = new ModelAndView("cart");
        //购物车所有商品
        modelAndView.addObject("map",indexService.getGwc(userId));
        modelAndView.addObject("userId",userId);

        return modelAndView;
    }

    ////更改购物车数量
    @RequestMapping("/dealGwcAdd")
    @ResponseBody
    public String dealGwcAdd(int custid,int foodid, int count){
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


    //添加新物品至购物车
    @RequestMapping("/addGwc")
    public void addGwc(Gwc gwc, HttpServletResponse response,
                               HttpServletRequest request) throws IOException {
        userService.addGwc(gwc);
        response.sendRedirect("cart?userId=" + gwc.getCust_id());
    }

    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/confirm_order")
    public ModelAndView confirm_order(int userId,String gwcId){
        ModelAndView modelAndView = new ModelAndView("confirmOrder");
        OrderModel orderModel = userService.dealConfirmOrder(userId,gwcId);
        modelAndView.addObject("orderModel",orderModel);
        modelAndView.addObject("address",userService.getAddress(userId));

        return modelAndView;
    }


    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/submitOrder")
    @ResponseBody
    public String submit(Order order){
        String res = userService.submitOrder(order);
        return res;
    }

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

    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/request")
    public ModelAndView request(){
        ModelAndView modelAndView = new ModelAndView("respond");


        return modelAndView;
    }

}
