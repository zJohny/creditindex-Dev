package com.zy.creditindex.controller.indexandidri;

import com.zy.creditindex.entity.idri.AdminUser;
import com.zy.creditindex.entity.idri.BastrdtINFOBean;
import com.zy.creditindex.entity.idri.IdriBean;


import com.zy.creditindex.service.IndexService.IdriService;
import com.zy.creditindex.util.DateUtil;
import com.zy.creditindex.util.IdriUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ${ZhaoYing}on 2017/10/23 0023
 */
@RestController
@RequestMapping(value = "/idri")
public class IdriContorller {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    protected static final Logger log = LoggerFactory.getLogger(IdriContorller.class);
    @Autowired
    private IdriService idriService;
    //初始化权重
    private String weighttype = "01";//默认等权
    @Autowired
    private CacheManager cacheManager;

    /**
     * 八个行业信贷风险指数排名
     * 参数：
     * 1.（Yoyg）行业内排名：同比环比（默认获取），当前时间（默认获取）
     * 2.（weighttype）加权类型（总页面获取,默认等权）//01：等权；02：债券加权
     */
    @PostMapping("/queryIndexdateNew")
    public List<IdriBean> queryIndexdateNew(String Yoyg, String weighttype){//Yoyg
        log.info("排名类型："+Yoyg);
        log.info("加权类型："+weighttype);
        List<IdriBean> indexdateNew = null;//接收返回的值，不用重新new新的ArrayList
        List<IdriBean> beforeidri = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            int week = DateUtil.getWeekend();
            Date endtime = DateUtil.endtime();//当前时间（昨天）
            if(Yoyg.equals("yer")){
                Date oneyer = DateUtil.oneYer();//一年前的日期
                BastrdtINFOBean bday = this.effectiveDate(oneyer);//获取有效日期
                Date oneyestrd_day = bday.getTrd_day();//获取一年前的最近交易有效日
                beforeidri  = idriService.findIndexdateNew(oneyestrd_day, weighttype);//获取去年的数据
                BastrdtINFOBean ybday = this.effectiveDate(endtime);//获取昨天的日期
                Date yestrd_day = ybday.getTrd_day();//获取昨天对应的最近交易日
                indexdateNew =idriService.findIndexdateNew(yestrd_day,weighttype);//(获取昨天的数据);
                indexdateNew =ProportionalValue(indexdateNew,beforeidri);//同比环比计算
                indexdateNew =IdriUtil.idriName(indexdateNew);
            }else if(Yoyg.equals("months")){
                BastrdtINFOBean  bday = this.effectiveDate(endtime);//获取昨天对应的是上个月
                Date onemonth = bday.getB_1mDay();//获取上个月对应的最近有效日期
                beforeidri  = idriService.findIndexdateNew(onemonth,weighttype);//获取上月的数据
                BastrdtINFOBean ybday = this.effectiveDate(endtime);//获取昨天的日期
                Date yestrd_day = ybday.getTrd_day();//获取昨天对应的最近交易日
                indexdateNew =idriService.findIndexdateNew(yestrd_day,weighttype);//(获取昨天的数据);
                indexdateNew =ProportionalValue(indexdateNew,beforeidri);//同比环比计算
                indexdateNew =IdriUtil.idriName(indexdateNew);
            }else if(Yoyg.equals("week")){
                BastrdtINFOBean bday = this.effectiveDate(endtime);//获取上周的日期
                Date lastweek = bday.getB_5dDay();//获取上周对应的最近交易日
                beforeidri  = idriService.findIndexdateNew(lastweek,weighttype);//获取上周数据
                BastrdtINFOBean ybday = this.effectiveDate(endtime);//获取昨天的日期
                Date yestrd_day = ybday.getTrd_day();//获取昨天对应的最近交易日
                indexdateNew =idriService.findIndexdateNew(yestrd_day,weighttype);//(获取昨天的数据);
                indexdateNew =ProportionalValue(indexdateNew,beforeidri);//同比环比计算
                indexdateNew =IdriUtil.idriName(indexdateNew);//X轴的汉字转换
            }else {
                if(week==1||week==7){
                    BastrdtINFOBean  bday = this.effectiveDate(endtime);//拿到最近有效交易日trd_day
                    Date trd_day = bday.getTrd_day();
                    Date beforeTradingDay = DateUtil.thedayBeforeTheLatestTradingDay(trd_day);//最近交易日的前一天
                    List<IdriBean> beforeTradingidri = idriService.DailyChain(beforeTradingDay, weighttype);//最近交易日前一天的数据
                    indexdateNew  =  idriService.findIndexdateNew(trd_day,weighttype);//周末没有交易(默认查询最近交易日)
                    indexdateNew =ProportionalValue(indexdateNew,beforeTradingidri);//同比环比计算
                    indexdateNew =IdriUtil.idriName(indexdateNew);
                }else {
                    Calendar calendar = Calendar.getInstance();//日历对象
                    calendar.setTime(new Date());//设置当前日期
                    Date  dayTime= calendar.getTime();
                    Date nowTime = dateFormat.parse(format.format(dayTime)+" 09:00:01");
                    BastrdtINFOBean ytrd_day = this.effectiveDate(endtime);//拿到昨天最近有效交易日yesterdaytrd_day
                    Date yesterdaytrd_day = ytrd_day.getTrd_day();
                    if(dayTime.getTime()<nowTime.getTime()){//如果当前日期小于九点
                        calendar.setTime(ytrd_day.getTrd_day());
                        calendar.add(Calendar.DATE, -2);//日期减一
                        ytrd_day = this.effectiveDate(calendar.getTime());
                        indexdateNew =idriService.findIndexdateNew(ytrd_day.getTrd_day(),weighttype);//昨天的数据
                    }else {
                        indexdateNew =idriService.findIndexdateNew(yesterdaytrd_day,weighttype);//昨天的数据
                    }
                    calendar.setTime(ytrd_day.getTrd_day());//拿到昨天的数据
                    calendar.add(Calendar.DATE, -1);//昨天的日期减一得到前天的数据
                    BastrdtINFOBean bday = this.effectiveDate(calendar.getTime());//拿到前天最近有效交易日beforetrd_day
                    Date beforetrd_day = bday.getTrd_day();
                    if(dayTime.getTime()<nowTime.getTime()){
                        calendar.setTime(ytrd_day.getTrd_day());
                        calendar.add(Calendar.DATE, -1);//日期减三
                        bday = this.effectiveDate(calendar.getTime());
                        beforeidri = idriService.DailyChain(bday.getTrd_day(), weighttype);//前天的数据
                    }else {
                        beforeidri = idriService.DailyChain(beforetrd_day, weighttype);//昨天的数据
                    }
                    indexdateNew =ProportionalValue(indexdateNew,beforeidri);//同比环比计算
                    indexdateNew =IdriUtil.idriName(indexdateNew);//X轴的汉字转换
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indexdateNew;
    }

    /**
     * 同比环比算法
     * @param idriBean
     * @param beforeidri
     * @return
     */
    public  List<IdriBean> ProportionalValue(List<IdriBean> idriBean,List<IdriBean> beforeidri){//List<IdriBean> idriBean,List<IdriBean> beforeidri
        List<IdriBean> idri = new ArrayList<IdriBean>();
        for (IdriBean idb:idriBean) {
            for (IdriBean bf:beforeidri) {
                if(idb.getInducode().equals(bf.getInducode())){
                    idb.setIdri(IdriUtil.CalculationIdri(idb.getIdri(),bf.getIdri()));//除
                    idri.add(idb);
                }
            }
        }
        Collections.sort(idri);//排个序升序
        Collections.reverse(idri);//reverse-->>>即可反转降序
        return idri;
    }

    /**
     * 获取最近有效交易日
     * @param atThatTime
     * @return
     */
    public BastrdtINFOBean effectiveDate(Date atThatTime){
        BastrdtINFOBean day = idriService.findRecentTradingDay(atThatTime);//获取最近交易日--》effective有效
        return day;
    }

    @GetMapping("/queryId")
    public List<IdriBean> queryId(@RequestParam(value = "id") String id){
       return  idriService.findIdriId(id);
    }
    //清除缓存方法
    @GetMapping("/removedCache")
    public String removedCache(){
        cacheManager.getCache("basecache").clear();
        return "Cache removed";
    }

    @Value("${value.commenStrig}")
    private String commenStrig;

    @Value("${value.commenInt:1233}")
    private int commenInt;

    @RequestMapping("/value_common")
    public String valueIs(){
        log.info("value.commenStrig is:"+commenStrig+commenInt);
        return "success";
    }
//
//    @GetMapping("/modul")
//    public List<AdminUser> testmm(){
//        return idriService.testModul();
//    }
}
