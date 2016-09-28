package com.main.model;

public class Recommend {
	private int recommend_id;//推荐位id
	private int recommend_type;//推荐类型：1为轮播图，2为精品推荐，3为猜你喜欢
	private String recommend_title;//推荐为标题，如：精品推荐
	private String recommend_logo;//推荐位图标，可以为空
	private String appId;//对应app的id
	public Recommend(int recommend_id, int recommend_type, String recommend_title, String recommend_logo, String appId) {
		super();
		this.recommend_id = recommend_id;
		this.recommend_type = recommend_type;
		this.recommend_title = recommend_title;
		this.recommend_logo = recommend_logo;
		this.appId = appId;
	}
	public int getRecommend_id() {
		return recommend_id;
	}
	public void setRecommend_id(int recommend_id) {
		this.recommend_id = recommend_id;
	}
	public int getRecommend_type() {
		return recommend_type;
	}
	public void setRecommend_type(int recommend_type) {
		this.recommend_type = recommend_type;
	}
	public String getRecommend_title() {
		return recommend_title;
	}
	public void setRecommend_title(String recommend_title) {
		this.recommend_title = recommend_title;
	}
	public String getRecommend_logo() {
		return recommend_logo;
	}
	public void setRecommend_logo(String recommend_logo) {
		this.recommend_logo = recommend_logo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Recommend() {
		super();
	}
	@Override
	public String toString() {
		return "Recommend [recommend_id=" + recommend_id + ", recommend_type=" + recommend_type + ", recommend_title="
				+ recommend_title + ", recommend_logo=" + recommend_logo + ", appId=" + appId + "]";
	}
	
	
}
