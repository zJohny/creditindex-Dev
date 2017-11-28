package com.zy.creditindex.repostory.indexJpa;

import com.zy.creditindex.entity.idri.IdriBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by ${ZhaoYing}on 2017/10/23 0023
 */
public interface IdriRepostory extends JpaRepository<IdriBean,String> {
    //当天的违约指数
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where  i.index_date=?1 and i.weight_type=?2 and i.indu_code IN('B','C','D','E','F','G','I','K') order by i.idri desc",nativeQuery = true)
    public List<IdriBean> findByIndexdate(Date indexdate, String weighttype);
    //通过id查询
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where i.indu_code = ?1",nativeQuery = true)
    public List<IdriBean> findByID(String inducode);

    //行业代码和指数计算日期联合查询
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where i.indu_code = ?1 and i.index_date=?2",nativeQuery = true)
    public List<IdriBean> findIdriByCodeAndDate(String inducode, Date indexdate);

    //加权类型,行业代码,指数计算日期联合查询
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where i.indu_code = ?1 and i.index_date=?2 and i.weight_type = ?3",nativeQuery = true)
    public IdriBean findIdriByCDT(String inducode, Date indexdate, String weighttype);

    //任意时间段查询
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where  i.index_date>=?1 and i.index_date<=?2 order by i.index_date asc",nativeQuery = true)
    public List<IdriBean> findByTimesTotto(Date starttime, Date endtime);

    //行业信贷违约指数和时间联合查询
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where  i.idri=?1 and i.index_date=?2 order by i.index_date asc",nativeQuery = true)
    public List<IdriBean> finfByIdriAndDate(BigDecimal idri, Date indexdate);
    /*根据日期和加权类型查询数据*/
    @Query(value="select  i.indu_code, i.index_date,i.idri ,i.weight_type ,i.remark,i.corp_count from idri i where i.index_date>=?1 and i.index_date<=?2 and i.weight_type=?3 and i.indu_code IN('B','C','D','E','F','G','I','K')  ORDER BY i.indu_code",nativeQuery = true)
    public List<IdriBean> queryIdriByCondition(Date startTime, Date endTime, String weightType );
    /*根据日期和加权类型查询10天交易日的数据降序*/
    @Query(value="select  i.indu_code, i.index_date,i.idri ,i.weight_type ,i.remark,i.corp_count from idri i where i.index_date>=?1 and i.index_date<=?2 and i.weight_type=?3 and i.indu_code IN('B','C','D','E','F','G','I','K')  ORDER BY i.indu_code,i.index_date DESC",nativeQuery = true)
    public List<IdriBean> queryIdriByGradeDown(Date startTime, Date endTime, String weightType );
    /*根据日期行业类型查询数据*/
    @Query(value="select  i.indu_code, i.index_date,i.idri ,i.weight_type ,i.remark,i.corp_count from idri i where i.index_date>=?1 and i.index_date<=?2 and i.weight_type=?3 and i.indu_code =?4",nativeQuery = true)
    public List<IdriBean> queryIdriByTrade(Date startTime, Date endTime, String type, String trade);
    /*根据日期行业类型查询数据*/
    @Query(value="select  i.indu_code, i.index_date,i.idri ,i.weight_type ,i.remark,i.remark, i.corp_count from idri i where i.index_date<=?1 and i.weight_type=?2 and i.indu_code IN('B','C','D','E','F','G','I','K') ",nativeQuery = true)
    public List<IdriBean> queryAllIdri(Date endTime, String type);

    /**
     * 查询同比环比的数据
     * @param indexdate
     * @param weighttype
     * @return
     */
    @Query(value ="select i.indu_code,i.index_date,i.weight_type,i.corp_count,i.idri,i.remark from idri i where  i.index_date=?1 and i.weight_type=?2 and i.indu_code in('B','C','D','E','F','G','I','K') order by i.indu_code asc",nativeQuery = true)
    public List<IdriBean> findByIAndW(Date indexdate, String weighttype);

}
