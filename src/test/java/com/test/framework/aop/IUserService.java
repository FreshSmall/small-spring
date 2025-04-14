package com.test.framework.aop;

import java.util.Random;

/**
 * @author: yinchao
 * @ClassName: IUserService
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/14 23:38
 */
public interface IUserService {

    public String queryUserInfo();

    public String register(String userName);
}
