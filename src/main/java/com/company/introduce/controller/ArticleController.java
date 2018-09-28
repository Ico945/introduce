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

import java.util.*;

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

    // 服务列表页
    @RequestMapping("/service")
    public String service(Model model) {
        List<Products> products = productsService.list();
        List<Products> products_3days = products.subList(0, 3);
        model.addAttribute("products", products);
        model.addAttribute("products_3days", products_3days);
        return "service";
    }

    // 服务详细页
    @RequestMapping("/service/article/{id}")
    public String service_one(Model model, @PathVariable String id) {
        int previous_id = 0;
        int next_id = 0;
        List<Products> products = productsService.list();
        Collections.sort(products, new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        Products product = productsService.findById(id);

        if (products.indexOf(product)==0) {
            if (products.size()>1)
                next_id = products.get(1).getId();
        } else if (products.indexOf(product)==products.size()-1) {
            if (products.size()>1)
                previous_id = products.get(products.size()-2).getId();
        } else {
            previous_id = products.get(products.indexOf(product)-1).getId();
            next_id = products.get(products.indexOf(product)+1).getId();
        }

        model.addAttribute("previous_id", previous_id);
        model.addAttribute("next_id", next_id);
        model.addAttribute("article", product);
        return "article(products)";
    }

    // 获得不同排序的两个文章列表，新闻列表页
    @RequestMapping("/news")
    public String news(Model model) {
        List<Article> articles_sort_by_view = articleService.list();
        Collections.sort(articles_sort_by_view, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getViews()-o1.getViews();
            }
        });
        model.addAttribute("articles_sort_by_view", articles_sort_by_view);

        List<Article> articles_sort_by_date = new ArrayList<>(articles_sort_by_view);
        Collections.sort(articles_sort_by_date, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        model.addAttribute("articles_sort_by_date", articles_sort_by_date);
        return "news";
    }

    // 新闻详细页
    @RequestMapping("/news/article/{id}")
    public String news_one(Model model, @PathVariable String id) {
        int previous_id = 0;
        int next_id = 0;
        List<Article> articles = articleService.list();
        // 按时间排序
        Collections.sort(articles, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        Article article = articleService.findById(id);
        if (articles.indexOf(article)==0) {
            if (articles.size()>1)
                next_id = articles.get(1).getId();
        } else if (articles.indexOf(article)==articles.size()-1) {
            if (articles.size()>1)
                previous_id = articles.get(articles.size()-2).getId();
        } else {
            previous_id = articles.get(articles.indexOf(article)-1).getId();
            next_id = articles.get(articles.indexOf(article)+1).getId();
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
