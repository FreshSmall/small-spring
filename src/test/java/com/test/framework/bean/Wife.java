package com.test.framework.bean;

/**
 * @author: yinchao
 * @ClassName: Wife
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:24
 */
public class Wife {

    private Husband husband;
    private IMother mother; // 婆婆

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

    public IMother getMother() {
        return mother;
    }

    public void setMother(IMother mother) {
        this.mother = mother;
    }

    public String queryHusband() {
        return "Wife.husband、Mother.callMother：" + mother.callMother();
    }

}
