package com.company.introduce.controller;

import com.company.introduce.aspect.MD5Util;
import com.company.introduce.aspect.WebSecurityConfig;
import com.company.introduce.entity.Article;
import com.company.introduce.entity.User;
import com.company.introduce.service.ArticleService;
import com.company.introduce.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    // 后台主页
    @RequestMapping(value = "", produces = {"application/json;charset=UTF-8"})
    public List<Article> all(){
        return articleService.list();
    }

    // 登录模块
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }

    //登录验证
    @RequestMapping("/dologin")
    public String dologin(HttpServletResponse response, User user){
        if(userService.login(user.getUsername(), new MD5Util(user.getUsername()).encode(user.getPassword()))){
            Cookie cookie = new Cookie(WebSecurityConfig.SESSION_KEY, user.toString());
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            System.out.println(cookie.getName());
            return "登录成功";
        }else {
            return "登录失败";
        }
    }

    // 删除文章
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        articleService.delete(id);
        return "删除成功";
    }

    // 写文章
    @RequestMapping("/write")
    public String write(){
        Article article = new Article();
        return "写文章界面";
    }

    // 存文章
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Article article){
        articleService.save(article);
        return "存储成功，返回管理首页";
    }

    // 修改文章
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String id){
        Article target = articleService.findById(id);
        Article article = new Article();
        return "跳转到编辑界面，然后调用save方法";
    }
}
