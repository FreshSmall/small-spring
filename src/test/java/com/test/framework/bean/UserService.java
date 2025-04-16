package com.test.framework.bean;

import com.demo.framework.stereotype.Component;

import java.util.Random;

/**
* @author: yinchao
* @ClassName: UserService
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:39
*/
@Component("userService")
public class UserService implements IUserService {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserService(){

    }

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

}
