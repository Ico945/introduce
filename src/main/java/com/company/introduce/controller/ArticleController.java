package com.company.introduce.controller;

import com.company.introduce.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/")
    public String index(Model model) {
        List list = articleService.list();
        if (list.size()<=3)
            model.addAttribute(list);
        else
            model.addAttribute(list.subList(0, 1));
        return "index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/service")
    public String shop(Model model) {
        return "service";
    }

    @RequestMapping("/news")
    public String shop_details(Model model) {
        return "news";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }


}
