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
        List<Products> products = productsService.list();
        model.addAttribute("products", products);
        return "service";
    }

    @RequestMapping("/service/article/{id}")
    public String service_one(Model model, @PathVariable String id) {
        int previous_id = 0;
        int next_id = 0;
        List<Article> articles = articleService.list();
        Article article = articleService.findById(id);
        if (articles.indexOf(article)==0) {
            if (articles.size()>1)
                next_id = articles.get(1).getId();
        }
        if (articles.indexOf(article)==articles.size()-1) {
            if (articles.size()>1)
                previous_id = articles.get(articles.size()-2).getId();
        }

        model.addAttribute("previous_id", previous_id);
        model.addAttribute("next_id", next_id);
        model.addAttribute("article", article);
        return "article(products)";
    }

    @RequestMapping("/news")
    public String news(Model model) {
        List<Article> articles = articleService.list();
        model.addAttribute("articles", articles);
        return "news";
    }
    @RequestMapping("/news/article/{id}")
    public String news_one(Model model, @PathVariable String id) {
        int previous_id = 0;
        int next_id = 0;
        List<Article> articles = articleService.list();
        Article article = articleService.findById(id);
        if (articles.indexOf(article)==0) {
            if (articles.size()>1)
                next_id = articles.get(1).getId();
        }
        if (articles.indexOf(article)==articles.size()-1) {
            if (articles.size()>1)
                previous_id = articles.get(articles.size()-2).getId();
        }

        model.addAttribute("previous_id", previous_id);
        model.addAttribute("next_id", next_id);
        model.addAttribute("article", article);
        return "article";
    }


    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }


}
