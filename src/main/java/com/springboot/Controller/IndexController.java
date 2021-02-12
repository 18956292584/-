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

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("allfood",indexService.allFood());
        model.addObject("allbusiness",indexService.allbusiness());
        return model;
    }

    @RequestMapping("/load")
    public ModelAndView load(){
        return new ModelAndView("load");
    }

}
