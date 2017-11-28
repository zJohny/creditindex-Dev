package com.zy.creditindex.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 列表图左侧行业名称及编码 开始日期截止日期
 * @author huaqin
 * @date 2017/11/1
 */
public class JsonEntry implements Serializable {
	/**/
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String creditorType;
	private String startTime;
	private String endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreditorType() {
		return creditorType;
	}
	public void setCreditorType(String creditorType) {
		this.creditorType = creditorType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
