package com.springboot.Controller;

import com.springboot.Repository.IndexService;
import com.springboot.Repository.UserService;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    //访问地址： localhost:9090/index/shop?shopid=1
    @RequestMapping("/shop")
    public ModelAndView shop(int shopid){
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("shop",indexService.getshop(shopid));
        modelAndView.addObject("shopfood",indexService.shopfood(shopid));
        modelAndView.addObject("othershop",indexService.othershop(shopid));
       // System.out.println(indexService.evaluateByshop(shopid).get(0).e_evaluate);
        modelAndView.addObject("evaluates",indexService.evaluateByshop(shopid));

        return modelAndView;
    }

    //访问地址： localhost:9090/index/detailsp?foodid=1
    @RequestMapping("/detailsp")
    public ModelAndView detailsp(int foodid){
        ModelAndView modelAndView = new ModelAndView("detailsp");
        modelAndView.addObject("food",indexService.getFood(foodid));
        modelAndView.addObject("top3food",indexService.top3food());
        modelAndView.addObject("foodEvaluate",indexService.evaluateByshop(foodid));

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

    //访问地址： localhost:9090/index/cart?userId=123
    @RequestMapping("/cart")
    public ModelAndView cart(int userId){
        ModelAndView modelAndView = new ModelAndView("cart");
        //购物车所有商品
        modelAndView.addObject("map",indexService.getGwc(userId));

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

}
