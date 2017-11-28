package com.zy.creditindex.entity.idri;

import com.zy.creditindex.entity.pk.IdriPK;
import javax.persistence.*;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${ZhaoYing}on 2017/10/23 0023
 */
@Entity
@IdClass(IdriPK.class)
@Table(name = "idri")
public class IdriBean implements Serializable,Comparable<IdriBean>{
	private static final long serialVersionUID = 1L;
	private static Map<String, String> map;
	// PRIMARY KEY(indu_code, index_date, weight_type)
	private String inducode;// indu_code VARCHAR(4), -- 行业代码
	private Date indexdate;// index_date DATE, -- 指数计算日期
	private String weighttype; // weight_type VARCHAR(4), -- 加权类型（01：等权；02：债券加权）
	private Integer corpcount; // corp_count INT, -- 公司个数。真正计算在内的公司
	private BigDecimal idri;// idri DECIMAL(30, 4), -- 行业信贷违约指数
	private String remark;//对异常计算结果的说明
	static {
		map = new HashMap<String, String>();
		map.put("B", "采矿业");
		map.put("C", "制造业");
		map.put("D", "电力、热力、燃气及水生产和供应业");
		map.put("E", "建筑业");
		map.put("F", "批发和零售业");
		map.put("G", "交通运输、仓储和邮政业");
		map.put("I", "信息传输、软件和信息技术服务业");
		map.put("K", "房地产业");
	}

	@Id
	@Column(name = "indu_code")
	public String getInducode() {
		return inducode;
	}

	public void setInducode(String inducode) {
		this.inducode = inducode;
	}

	@Id
	@Column(name = "index_date")
	public Date getIndexdate() {
		return indexdate;
	}

	public void setIndexdate(Date indexdate) {
		this.indexdate = indexdate;
	}

	@Id
	@Column(name = "weight_type")
	public String getWeighttype() {
		return weighttype;
	}

	public void setWeighttype(String weighttype) {
		this.weighttype = weighttype;
	}

	@Column(name = "corp_count")
	public Integer getCorpcount() {
		return corpcount;
	}

	public void setCorpcount(Integer corpcount) {
		this.corpcount = corpcount;
	}

	public BigDecimal getIdri() {
		return idri;
	}

	public void setIdri(BigDecimal idri) {
		this.idri = idri;
	}

	public static Map<String, String> getMap() {
		return map;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "IdriBean{" +
				"inducode='" + inducode + '\'' +
				", indexdate=" + indexdate +
				", weighttype='" + weighttype + '\'' +
				", corpcount=" + corpcount +
				", idri=" + idri +
				", remark='" + remark + '\'' +
				", oneHundred=" + oneHundred +
				'}';
	}

	BigDecimal oneHundred = new BigDecimal(Double.toString(100));

	@Override
	//实现Comparable的compareTo方法
	public int compareTo(IdriBean stu) {
		// TODO Auto-generated method stub  注意 参数传入的 顺序
		return (this.getIdri().multiply(oneHundred)).intValue()-((stu.getIdri().multiply(oneHundred)).intValue());
	}

}
