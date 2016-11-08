package com.main.model;

import java.util.List;

import com.main.utils.Constance;
import com.main.utils.VerifyUtils;

public class Subject {
	private String id;
    private String title;
    private String des;
    private String logo;
    private String idString;
    private long updateTime;
    private int sort;
    private List<App> apps;
    private String dateTime;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getLogo() {
		if (!VerifyUtils.isUrl(logo)) {
			return Constance.BASE_URL+logo;
		}
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public Subject(String id, String title, String des, String logo, String idString,long updateTime,int sort) {
		super();
		this.id = id;
		this.title = title;
		this.des = des;
		this.logo = logo;
		this.idString = idString;
		this.updateTime = updateTime;
		this.sort = sort;
	}
	public Subject() {
		super();
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public List<App> getApps() {
		return apps;
	}
	public void setApps(List<App> apps) {
		this.apps = apps;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
    
    
}
