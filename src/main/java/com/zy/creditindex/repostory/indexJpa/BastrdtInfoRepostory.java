package com.zy.creditindex.repostory.indexJpa;

import com.zy.creditindex.entity.idri.BastrdtINFOBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by huaqin on 2017/10/31.
 */
@Repository
public interface BastrdtInfoRepostory extends JpaRepository<BastrdtINFOBean,Integer>{
    /*通过日期查询交易日*/
    @Query(value="SELECT bs.id,bs.NORM_DAY,bs.EXCH_CODE,bs.NXT_DAY,bs.PRE_DAY,bs.IS_TRD_DAY,bs.THIS_W,bs.TRD_DAY,bs.THIS_W_N,bs.THIS_M_N,bs.LST_W_DAY,bs.LST_M_DAY,bs.LST_Y_DAY,bs.IS_END_W,bs.IS_END_M,bs.IS_END_Q,bs. IS_END_Y,bs.IS_LST_END_M,bs.IS_LST_END_Q,bs.ENT_TIME,bs.UPD_TIME,bs.GRD_TIME,bs.RS_ID,bs.REC_ID,bs.B_5D_DAY,bs.B_10D_DAY,bs.B_1M_DAY,bs.B_2M_DAY,bs.B_3M_DAY,bs.N_TRD_DAY_TYP FROM BAS_TRDT_INFO bs  WHERE bs.NORM_DAY=?1 and bs.EXCH_CODE in('101','105')",nativeQuery = true)
    public BastrdtINFOBean queryStartTime(Date dateTime);
    /*查询10天前的交易日*/
    @Query(value="SELECT  bs.id,bs.NORM_DAY,bs.EXCH_CODE,bs.NXT_DAY,bs.PRE_DAY,bs.IS_TRD_DAY,bs.THIS_W,bs.TRD_DAY,bs.THIS_W_N,bs.THIS_M_N,bs.LST_W_DAY,bs.LST_M_DAY,bs.LST_Y_DAY,bs.IS_END_W,bs.IS_END_M,bs.IS_END_Q,bs. IS_END_Y,bs.IS_LST_END_M,bs.IS_LST_END_Q,bs.ENT_TIME,bs.UPD_TIME,bs.GRD_TIME,bs.RS_ID,bs.REC_ID,bs.B_5D_DAY,bs.B_10D_DAY,bs.B_1M_DAY,bs.B_2M_DAY,bs.B_3M_DAY,bs.N_TRD_DAY_TYP FROM BAS_TRDT_INFO bs  WHERE NORM_DAY<=?1 and EXCH_CODE in('101','105') and is_trd_day='1' ORDER BY trd_day DESC limit 9,1",nativeQuery = true)
    public BastrdtINFOBean queryBeforTime(Date dateTime);
    @Query(value="SELECT  bs.id,bs.NORM_DAY,bs.EXCH_CODE,bs.NXT_DAY,bs.PRE_DAY,bs.IS_TRD_DAY,bs.THIS_W,bs.TRD_DAY,bs.THIS_W_N,bs.THIS_M_N,bs.LST_W_DAY,bs.LST_M_DAY,bs.LST_Y_DAY,bs.IS_END_W,bs.IS_END_M,bs.IS_END_Q,bs. IS_END_Y,bs.IS_LST_END_M,bs.IS_LST_END_Q,bs.ENT_TIME,bs.UPD_TIME,bs.GRD_TIME,bs.RS_ID,bs.REC_ID,bs.B_5D_DAY,bs.B_10D_DAY,bs.B_1M_DAY,bs.B_2M_DAY,bs.B_3M_DAY,bs.N_TRD_DAY_TYP FROM BAS_TRDT_INFO bs  WHERE bs.trd_day >=?1 and bs.TRD_DAY<=?2 and bs.EXCH_CODE in('101','105')  and bs.IS_TRD_DAY='1' ORDER BY bs.TRD_DAY desc",nativeQuery = true)
    public List<BastrdtINFOBean> findWithinTenDay(Date startTime,Date endTime);

    /*查询最近交易日
     */
    @Query(value="select b.id,b.NORM_DAY,b.EXCH_CODE,b.NXT_DAY,b.PRE_DAY,b.IS_TRD_DAY,b.THIS_W,b.TRD_DAY,b.THIS_W_N,b.THIS_M_N,b.LST_W_DAY,b.LST_M_DAY,b.LST_Y_DAY,b.IS_END_W,b.IS_END_M,b.IS_END_Q,b.IS_END_Y,b.IS_LST_END_M,b.IS_LST_END_Q,b.ENT_TIME,b.UPD_TIME,b.GRD_TIME,b.RS_ID,b.REC_ID,b.B_5D_DAY,b.B_10D_DAY,b.B_1M_DAY,b.B_2M_DAY,b.B_3M_DAY,b.N_TRD_DAY_TYP from BAS_TRDT_INFO b  where b.NORM_DAY=?1 and b.EXCH_CODE in('101','105')",nativeQuery = true)
    public BastrdtINFOBean findByLatestDate(Date dateTime);
}
