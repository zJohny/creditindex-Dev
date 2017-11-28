package com.zy.creditindex.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询管理员权限类
 * @author huaqin
 * @date 2017/10/20
 */
public class XParameter implements Serializable{
    /*产业名称*/
    private String  label;
    /*日期*/
    private List<String> dateTime = new ArrayList<String>();
    /*线型展示连接参数*/
    private List<BigDecimal> data=new ArrayList<BigDecimal>();
    /*小图标颜色*/
    private String backgroundColor;
    /*折线颜色*/
    private String  borderColor;
    /*取消曲线区域展示*/
    private Boolean fill=false;
    /*判断是否等权加权*/
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public Boolean isFill() {
        return fill;
    }

    public void setFill(Boolean fill) {
        this.fill = fill;
    }
}
