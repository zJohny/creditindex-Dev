package com.zy.creditindex.entity.idri;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by huaqin on 2017/10/31.
 */
@Entity
@Table(name = "BAS_TRDT_INFO")
public class BastrdtINFOBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    /*NORM_DAY*/
    private Date normDay;
    /*EXCH_CODE*/
    private int exchCode;
    private Date trd_day;
    /*NXT_DAY*/
    private Date nxtDay;
    /*PRE_DAY*/
    private Date preDay;
    /*IS_TRD_DAY*/
    private String is_TRD_Day;
    /*THIS_W*/
    private Boolean this_W;
    /*THIS_W_N*/
    private Boolean this_W_N;
    /*THIS_M_N*/
    private Boolean this_M_N;
    /*LST_W_DAY*/
    private Date lst_W_Day;
    /*LST_M_DAY*/
    private Date lst_M_Day;
    /*LST_Y_DAY*/
    private Date lst_Y_Day;
    /*IS_END_W*/
    private String isEnd_w;
    /*IS_END_M*/
    private String isEnd_m;
    /*IS_END_Q*/
    private String isEnd_q;
    /*IS_END_Y*/
    private String isEnd_y;
    /*IS_LST_END_M*/
    private String isLstEnd_m;
    /*IS_LST_END_Q*/
    private String isLstEnd_q;
    /*ENT_TIME*/
    private Date entTime;
    /*UPD_TIME*/
    private Date updTime;
    /*GRD_TIME*/
    private Date grdTime;
    /*RS_ID*/
    private String rsId;
    /*REC_ID*/
    private String recId;
    /*B_5D_DAY*/
    private Date b_5dDay;
    /*B_10D_DAY*/
    private Date b_10dDay;
    /*B_1M_DAY*/
    private Date b_1mDay;
    /*B_2M_DAY*/
    private Date b_2mDay;
    /*B_3M_DAY*/
    private Date b_3mDay;
    /*N_TRD_DAY_TYP*/
    private Integer n_TRD_Day_typ;

    @Column(name = "TRD_DAY")
    public Date getTrd_day() {
        return trd_day;
    }

    public void setrd_day(Date trd_day) {
        this.trd_day = trd_day;
    }
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Column(name = "NORM_DAY")
    public Date getNormDay() {
        return normDay;
    }

    public void setNormDay(Date normDay) {
        this.normDay = normDay;
    }

    @Column(name = "EXCH_CODE")
    public int getExchCode() {
        return exchCode;
    }

    public void setExchCode(int exchCode) {
        this.exchCode = exchCode;
    }

    @Column(name = "NXT_DAY")
    public Date getNxtDay() {
        return nxtDay;
    }

    public void setNxtDay(Date nxtDay) {
        this.nxtDay = nxtDay;
    }

    @Column(name = "PRE_DAY")
    public Date getPreDay() {
        return preDay;
    }

    public void setPreDay(Date preDay) {
        this.preDay = preDay;
    }

    @Column(name = "IS_TRD_DAY")
    public String getIs_TRD_Day() {
        return is_TRD_Day;
    }

    public void setIs_TRD_Day(String is_TRD_Day) {
        this.is_TRD_Day = is_TRD_Day;
    }

    @Column(name = "THIS_W")
    public Boolean isthis_W() {
        return this_W;
    }
    @Column(name = "THIS_W_N")
    public Boolean isthis_W_N() {
        return this_W_N;
    }
    @Column(name = "THIS_M_N")
    public Boolean isthis_M_N() {
        return this_M_N;
    }

    public void setthis_M_N(Boolean this_M_N) {
        this.this_M_N = this_M_N;
    }

    public void setthis_W(Boolean this_W) {
        this.this_W = this_W;
    }

    public void setthis_W_N(Boolean this_W_N) {
        this.this_W_N = this_W_N;
    }
    @Column(name = "LST_W_DAY")
    public Date getlst_W_Day() {
        return lst_W_Day;
    }

    public void setlst_W_Day(Date lst_W_Day) {
        this.lst_W_Day = lst_W_Day;
    }

    @Column(name = "LST_M_DAY")
    public Date getlst_M_Day() {
        return lst_M_Day;
    }

    public void setlst_M_Day(Date lst_M_Day) {
        this.lst_M_Day = lst_M_Day;
    }

    @Column(name = "LST_Y_DAY")
    public Date getlst_Y_Day() {
        return lst_Y_Day;
    }

    public void setlst_Y_Day(Date lst_Y_Day) {
        this.lst_Y_Day = lst_Y_Day;
    }

    @Column(name = "IS_END_W")
    public String getIsEnd_w() {
        return isEnd_w;
    }

    public void setIsEnd_w(String isEnd_w) {
        this.isEnd_w = isEnd_w;
    }

    @Column(name = "IS_END_M")
    public String getIsEnd_m() {
        return isEnd_m;
    }

    public void setIsEndm(String isEnd_m) {
        this.isEnd_m = isEnd_m;
    }

    @Column(name = "ENT_TIME")
    public Date getEntTime() {
        return entTime;
    }

    public void setEntTime(Date entTime) {
        this.entTime = entTime;
    }

    @Column(name = "IS_END_Q")
    public String getIsEnd_q() {
        return isEnd_q;
    }

    public void setIsEnd_q(String isEnd_q) {
        this.isEnd_q = isEnd_q;
    }

    @Column(name = "IS_END_Y")
    public String getIsEnd_y() {
        return isEnd_y;
    }

    public void setIsEnd_y(String isEnd_y) {
        this.isEnd_y = isEnd_y;
    }

    @Column(name = "IS_LST_END_M")
    public String getIsLstEnd_m() {
        return isLstEnd_m;
    }

    public void setIsLstEnd_m(String isLstEnd_m) {
        this.isLstEnd_m = isLstEnd_m;
    }

    @Column(name = "IS_LST_END_Q")
    public String getIsLstEnd_q() {
        return isLstEnd_q;
    }

    public void setIsLstEnd_q(String isLstEnd_q) {
        this.isLstEnd_q = isLstEnd_q;
    }

    @Column(name = "UPD_TIME")
    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    @Column(name = "GRD_TIME")
    public Date getGrdTime() {
        return grdTime;
    }

    public void setGrdTime(Date grdTime) {
        this.grdTime = grdTime;
    }

    @Column(name = "RS_ID")
    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    @Column(name = "REC_ID")
    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }
    @Column(name = "B_5D_DAY")
    public Date getB_5dDay() {
        return b_5dDay;
    }

    public void setB_5dDay(Date b_5dDay) {
        this.b_5dDay = b_5dDay;
    }
    @Column(name = "B_10D_DAY")
    public Date getB_10dDay() {
        return b_10dDay;
    }

    public void setB_10dDay(Date b_10dDay) {
        this.b_10dDay = b_10dDay;
    }
    @Column(name = "B_1M_DAY")
    public Date getB_1mDay() {
        return b_1mDay;
    }

    public void setB_1mDay(Date b_1mDay) {
        this.b_1mDay = b_1mDay;
    }
    @Column(name = "B_2M_DAY")
    public Date getB_2mDay() {
        return b_2mDay;
    }

    public void setB_2mDay(Date b_2mDay) {
        this.b_2mDay = b_2mDay;
    }
    @Column(name = "B_3M_DAY")
    public Date getB_3mDay() {
        return b_3mDay;
    }

    public void setB_3mDay(Date b_3mDay) {
        this.b_3mDay = b_3mDay;
    }

    public Integer getN_TRD_Day_typ() {
        return n_TRD_Day_typ;
    }

    public void setN_TRD_Day_typ(Integer n_TRD_Day_typ) {
        this.n_TRD_Day_typ = n_TRD_Day_typ;
    }
}
