package com.zy.creditindex.entity.idri;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ${ZhaoYing}on 2017/11/27 0027
 */
@Data
@Table(name = "admin_user")
public class AdminUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;//    uid
    private  String by1;//            by1
    private  String by2; //    by2
    private Date createdtime;//            createdtime
    private  String name;//    name
    private  String password;//            password
    private  String  usernumber;//    usernumber   用户编号

}
