package com.company.introduce.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

//        // 排除配置
//        addInterceptor.excludePathPatterns("/article/**");

        // 拦截配置
        addInterceptor.addPathPatterns("/admin/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            System.out.println("拦截到");
            Cookie[] cookies = request.getCookies();
            String path =request.getServletPath();
            if(path.contains("login")){
                return true;
            }

            if(cookies != null) {
                for (Cookie cookie : cookies) {
                    System.out.println(cookie.getName() + "拦截器中的cookie");
                    if (cookie.getName().equals(SESSION_KEY)) {

                        return true;
                    }
                }
            }

            response.sendRedirect("/admin/login");

            return false;


        }
    }

}
