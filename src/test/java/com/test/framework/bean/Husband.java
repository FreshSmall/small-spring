package com.test.framework.bean;

/**
 * @author: yinchao
 * @ClassName: Husband
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:24
 */
public class Husband {

    private Wife wife;

    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
    }

    public String queryWife(){
        return "Husband.wife";
    }
}
