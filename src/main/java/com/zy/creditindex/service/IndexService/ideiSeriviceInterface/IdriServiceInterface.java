package com.zy.creditindex.service.IndexService.ideiSeriviceInterface;

import com.zy.creditindex.entity.idri.IdriBean;

import java.util.Date;
import java.util.List;

/**
 * Created by ${ZhaoYing}on 2017/11/6 0006
 */
public interface IdriServiceInterface {
    /**
     * 八个行业信贷风险指数排名
     * @param indexdate 查询日期（当前日期，同比，环比）
     * @param weighttype 加权类型（等权/加权）
     * @return
     */
    public List<IdriBean> findIndexdateNew(Date indexdate, String weighttype);
}
