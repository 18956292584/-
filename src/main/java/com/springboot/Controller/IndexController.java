package com.springboot.Controller;

import com.springboot.Repository.IndexService;
import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    IndexService indexService;

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

}
