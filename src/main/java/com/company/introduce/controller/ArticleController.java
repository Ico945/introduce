package com.company.introduce.controller;

import com.company.introduce.entity.Article;
import com.company.introduce.entity.Products;
import com.company.introduce.service.ArticleService;
import com.company.introduce.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    ProductsService productsService;

    @RequestMapping("/")
    public String index(Model model) {
        List list = articleService.list();
        if (list.size()<=3)
            model.addAttribute("articles", list);
        else
            model.addAttribute(list.subList(0, 3));
        return "index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/service")
    public String service(Model model) {
        return "service";
    }
    @RequestMapping("/service/{category}")
    public String service_one(Model model, @PathVariable String category) {
        List<Products> products = productsService.findByCategory(category);
        model.addAttribute("articles", products);
        return "service";
    }


    @RequestMapping("/news")
    public String news(Model model) {
        return "news";
    }
    @RequestMapping("/news/{category}")
    public String news_one(Model model, @PathVariable String category) {
        List<Article> articles = articleService.findByCategory(category);
        model.addAttribute("articles", articles);
        return "news";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }


}
