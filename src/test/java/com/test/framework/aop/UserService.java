package com.test.framework.aop;

import java.util.Random;

/**
* @author: yinchao
* @ClassName: UserService
* @Description: 
* @team wuhan operational dev.
* @date: 2025/4/14 23:39
*/
public class UserService implements IUserService {
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
}
