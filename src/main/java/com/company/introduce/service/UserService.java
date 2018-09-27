package com.company.introduce.service;

import com.company.introduce.dao.UserDao;
import com.company.introduce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean login(String username, String password){

        User user = userDao.findByUsernameAndPassword(username, password);
        if(null == user){
            // 登录失败
            return false;
        }else {
            // 登录成功
            return true;
        }
    }

//    public void set_nameandpsw(String username, String password){
//        userDao.
//    }
}
