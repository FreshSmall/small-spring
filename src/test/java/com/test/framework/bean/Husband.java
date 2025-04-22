package com.test.framework.bean;

import java.time.LocalDate;

/**
 * @author: yinchao
 * @ClassName: Husband
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/4/19 23:24
 */
public class Husband {

    private String wifiName;

    private LocalDate marriageDate;

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "wifiName='" + wifiName + '\'' +
                ", marriageDate=" + marriageDate +
                '}';
    }
}
