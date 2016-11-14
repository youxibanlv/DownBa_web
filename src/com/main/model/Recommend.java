package com.main.model;

public class Recommend {
	private int recommend_id;//推荐位id
	private int recommend_type;//推荐类型：1为轮播图，2为猜你喜欢
	private String recommend_title;//推荐为标题，如：精品推荐
	private String recommend_logo;//推荐位图标，可以为空
	private String appId;//对应app的id、
	private int sort;//排序
	private String update_time;//更新时间
	private App app;
	
	
	
	public Recommend(int recommend_id, int recommend_type, String recommend_title, String recommend_logo, String appId,
			int sort, String update_time) {
		super();
		this.recommend_id = recommend_id;
		this.recommend_type = recommend_type;
		this.recommend_title = recommend_title;
		this.recommend_logo = recommend_logo;
		this.appId = appId;
		this.sort = sort;
		this.update_time = update_time;
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
//		if (!VerifyUtils.isUrl(recommend_logo)) {
//			return Constance.BASE_URL+recommend_logo;
//		}else{
			return recommend_logo;
//		}
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
	

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	
	
	
}
