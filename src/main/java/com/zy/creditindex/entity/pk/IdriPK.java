package com.zy.creditindex.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ${ZhaoYing}on 2017/10/23 0023
 */
@Embeddable
public class IdriPK implements Serializable{
    private String inducode;// indu_code VARCHAR(4),   -- 行业代码
    private Date indexdate;//index_date DATE,        -- 指数计算日期
    private String weighttype ; //weight_type VARCHAR(4), -- 加权类型（01：等权；02：债券加权）

    @Column(name = "indu_code")
    public String getInducode() {
        return inducode;
    }

    public void setInducode(String inducode) {
        this.inducode = inducode;
    }
    @Column(name = "index_date")
    public Date getIndexdate() {
        return indexdate;
    }

    public void setIndexdate(Date indexdate) {
        this.indexdate = indexdate;
    }
    @Column(name = "weight_type")
    public String getWeighttype() {
        return weighttype;
    }

    public void setWeighttype(String weighttype) {
        this.weighttype = weighttype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdriPK idriPK = (IdriPK) o;

        if (inducode != null ? !inducode.equals(idriPK.inducode) : idriPK.inducode != null) return false;
        if (indexdate != null ? !indexdate.equals(idriPK.indexdate) : idriPK.indexdate != null) return false;
        return weighttype != null ? weighttype.equals(idriPK.weighttype) : idriPK.weighttype == null;
    }

    @Override
    public int hashCode() {
        int result = inducode != null ? inducode.hashCode() : 0;
        result = 31 * result + (indexdate != null ? indexdate.hashCode() : 0);
        result = 31 * result + (weighttype != null ? weighttype.hashCode() : 0);
        return result;
    }
}
