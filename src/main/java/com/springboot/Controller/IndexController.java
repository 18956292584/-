package com.springboot.Controller;

import com.alipay.api.AlipayApiException;
import com.springboot.Repository.Impl.PayAction;
import com.springboot.Repository.Impl.Util;
import com.springboot.Repository.IndexService;
import com.springboot.Repository.OrderService;
import com.springboot.Repository.UserService;
import com.springboot.entity.Gwc;
import com.springboot.entity.Order;
import com.springboot.entity.OrderModel;
import com.springboot.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    OrderService orderService;

    //访问地址： localhost:9090/index/
    @RequestMapping("/")
    public ModelAndView index(Model model1){
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
    //访问地址： http://localhost:9090/index/category?currentPage=1&paixu=f_price
    @RequestMapping("/category")
    public ModelAndView category(Page page){
        ModelAndView modelAndView = new ModelAndView("category");
        //购物车所有商品
        modelAndView.addObject("allfood",indexService.allFood(page));
        modelAndView.addObject("othershop",indexService.otherShop());
        modelAndView.addObject("page",indexService.setAllfoodPage(page));

        return modelAndView;
    }

    //美食页面
    //访问地址： http://localhost:9090/index/category?currentPage=1&paixu=f_id
    @RequestMapping("/currentPage")
    public ModelAndView currentPage(Page page){
        ModelAndView modelAndView = new ModelAndView("categoryPage");
        //购物车所有商品
        modelAndView.addObject("allfood",indexService.allFood(page));
        modelAndView.addObject("page",indexService.setAllfoodPage(page));

        return modelAndView;
    }

    //商家页面
    //访问地址： localhost:9090/index/shop?shopid=1
    @RequestMapping("/shop")
    public ModelAndView shop(int shopid,HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("shop");
        modelAndView.addObject("shop",indexService.getshop(shopid));
        modelAndView.addObject("shopfood",indexService.shopfood(shopid));
        modelAndView.addObject("othershop",indexService.othershop(shopid));
        modelAndView.addObject("evaluates",indexService.evaluateByshop(shopid));
        modelAndView.addObject("userid",Util.getUserId(request));

        return modelAndView;
    }

    //没事
    //访问地址： localhost:9090/index/detailsp?foodid=1
    @RequestMapping("/detailsp")
    public ModelAndView detailsp(int foodid,HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("detailsp");
        modelAndView.addObject("food",indexService.getFood(foodid));
        modelAndView.addObject("top3food",indexService.top3food());
        modelAndView.addObject("foodEvaluate",indexService.evaluateByfood(foodid));
        modelAndView.addObject("userid",Util.getUserId(request));

        return modelAndView;
    }

    //访问地址： http://localhost:9090/index/list?currentPage=1&paixu=b_score
    @RequestMapping("/list")
    public ModelAndView list(Page page){
        ModelAndView modelAndView = new ModelAndView("list");
        //所有商家
        modelAndView.addObject("allbusiness",indexService.allbusiness(page));
        modelAndView.addObject("othershop",indexService.otherShop());
        modelAndView.addObject("page",indexService.setAllBusPage(page));

        return modelAndView;
    }

    @RequestMapping("/listPage")
    public ModelAndView listPage(Page page){
        ModelAndView modelAndView = new ModelAndView("listPage");
        //所有商家
        modelAndView.addObject("allbusiness",indexService.allbusiness(page));
        modelAndView.addObject("page",indexService.setAllBusPage(page));

        return modelAndView;
    }

    //访问地址： localhost:9090/user/search_p?keyword=宫保鸡丁
    @RequestMapping("/search_p")
    public ModelAndView search_p(String keyword){
        ModelAndView modelAndView = new ModelAndView("search_p");
        modelAndView.addObject("allfood",indexService.searchFood(keyword));
        modelAndView.addObject("othershop",indexService.otherShop());

        return modelAndView;
    }

    //访问地址： localhost:9090/index/search_s?keyword=宝鸡
    @RequestMapping("/search_s")
    public ModelAndView search_s(String keyword){
        ModelAndView modelAndView = new ModelAndView("search_s");
        modelAndView.addObject("allbusiness",indexService.searchBus(keyword));
        modelAndView.addObject("othershop",indexService.otherShop());

        return modelAndView;
    }

}
