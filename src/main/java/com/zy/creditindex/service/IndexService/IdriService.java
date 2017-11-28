package com.zy.creditindex.service.IndexService;
import com.zy.creditindex.entity.idri.AdminUser;
import com.zy.creditindex.entity.idri.BastrdtINFOBean;
import com.zy.creditindex.entity.idri.IdriBean;

import com.zy.creditindex.repostory.indexJpa.BastrdtInfoRepostory;
import com.zy.creditindex.repostory.indexJpa.IdriRepostory;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Created by ${ZhaoYing}on 2017/10/23 0023
 * basecache
 */
@Service
@Transactional
@CacheConfig(cacheNames = "basecache")
public class IdriService {
    protected static final Logger log = LoggerFactory.getLogger(IdriService.class);
    @Autowired
    private IdriRepostory idriRepostory;

    @Autowired
    private BastrdtInfoRepostory bastrdtinfo;

//    @Autowired
//    private AdminUserRepostory userJpa;
    /**
     * id查询
     */
    @Cacheable
    public List<IdriBean> findIdriId(String id) {
        log.info("indu_code IS："+id+"：（行业代码）Start query······");
        List<IdriBean> byID = idriRepostory.findByID(id);
        log.info("indu_code IS："+id+"：（行业代码）End query······");
        return byID;
    }

    /**
     * 查询所有的数据
     *
     * @return
     */
    public List<IdriBean> findIdriAll() {
        return idriRepostory.findAll();
    }

    /**
     * 行业代码和指数计算日期联合查询
     *
     * @return
     */
    public List<IdriBean> findIdriByCodeAndDate(String inducode, Date indexdate) {
        return idriRepostory.findIdriByCodeAndDate(inducode, indexdate);
    }

    /**
     * 加权类型,行业代码,指数计算日期联合查询
     *
     * @return
     */
    public IdriBean findIdriByCDT(String inducode, Date indexdate, String weighttype) {
        return idriRepostory.findIdriByCDT(inducode, indexdate, weighttype);
    }

    /**
     * 任意时间段查询
     *
     * @param starttime
     * @param endtime
     * @return
     */
//    @Cacheable
    public List<IdriBean> findIdriByTimesTotto(Date starttime, Date endtime) {
        return idriRepostory.findByTimesTotto(starttime, endtime);
    }

    /**
     * 行业代码和加权等权计算日期联合查询
     *
     * @param startTime
     * @param endTime
     * @param weightType
     * @return
     */
    public List<IdriBean> queryIdriByCondition(Date startTime, Date endTime, String weightType) {
        return idriRepostory.queryIdriByCondition(startTime, endTime, weightType);
    }

    /**
     * 行业代码和加权等权计算日期联合查询降序
     *
     * @param startTime
     * @param endTime
     * @param weightType
     * @return
     */
    public List<IdriBean> queryIdriByGradeDown(Date startTime, Date endTime, String weightType) {
        return idriRepostory.queryIdriByGradeDown(startTime, endTime, weightType);
    }

    /**
     * 八个行业信贷风险指数排名
     *
     * @param indexdate  查询日期（当前日期，同比，环比）
     * @param weighttype 加权类型（等权/加权）
     * @return 可加缓存
     */
//    @Cacheable
    public List<IdriBean> findIndexdateNew(Date indexdate, String weighttype) {
        return idriRepostory.findByIndexdate(indexdate, weighttype);
    }

    /**
     * 根据日期行业类型查询数据
     * @param startTime
     * @param endTime
     * @param type
     * @param trade
     * @return
     */
    public List<IdriBean> queryIdriByTrade(Date startTime, Date endTime, String type, String trade) {
        return idriRepostory.queryIdriByTrade(startTime, endTime, type, trade);
    }

    /**
     * 根据日期行业类型查询数据
     * @param endTime
     * @param weightType
     * @return
     */
    public List<IdriBean> queryAllIdri(Date endTime, String weightType) {
        return idriRepostory.queryAllIdri(endTime, weightType);
    }

    /**
     * 查询最近交易日
     * @param dateTime（当当天日期是非交易日时）
     * @return
     * @Cacheable
     */
    public BastrdtINFOBean findRecentTradingDay(Date dateTime) {
        BastrdtINFOBean d = bastrdtinfo.findByLatestDate(dateTime);
        log.info("-==============最近交易日===================" + d.getTrd_day());
        return bastrdtinfo.findByLatestDate(dateTime);
    }

    /**
     * 总的同比环比
     * @param indexdate
     * @param weighttype
     * @return
     */
    public List<IdriBean> DailyChain(Date indexdate, String weighttype){
        return  idriRepostory.findByIndexdate(indexdate,weighttype);
    }

    /**
     * 同比环比计算数据
     * @param indexdate
     * @param weighttype
     * @return
     */
    public List<IdriBean> DailyChainls(Date indexdate, String weighttype){
        return idriRepostory.findByIAndW( indexdate, weighttype);
    }

//    public List<AdminUser> testModul(){
//        return userJpa.findAll();
//    }
}
