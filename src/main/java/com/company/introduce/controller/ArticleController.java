package com.company.introduce.controller;

import com.company.introduce.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @RequestMapping("/shop")
    public String shop(Model model) {
        return "shop";
    }

    @RequestMapping("/details")
    public String shop_details(Model model) {
        return "details";
    }

}
