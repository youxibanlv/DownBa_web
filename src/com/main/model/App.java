package com.main.model;

import com.main.utils.Constance;
import com.main.utils.TimeUtil;
import com.main.utils.VerifyUtils;

public class App {
	    private String app_id;//应用id
	    private String last_cate_id;//终极分类ID
	    private String app_title;//应用名称
	    private String app_stitle;//SEO标题
	    private String app_version;//版本号（最新）
	    private String app_update_time;//更新时间（最新）
	    private String app_size;//程序大小（最新）
	    private String app_system;//系统要求（最新）
	    private String app_type;//软件类型：免费/收费
	    private String app_logo;//缩略图
	    private String app_desc;//应用详情
	    private String app_company;//开发商
	    private String app_company_url;//开发商网址
	    private String app_tags;//标签
	    private String create_time;//入库时间
	    private String app_grade;//用户评分
	    private String app_recomment;//系统评分
	    private String uid;//发布人ID
	    private String uname;//发布人
	    private String app_comments;//评论量
	    private String app_visitors;//浏览量
	    private String app_down;//下载量
	    private String app_order;//排序
	    private String data_app_id;//数据中心应用ID
	    private String charge_app_id;//独立计费包ID
	    private String rewrite_tag;//app页面伪静态标签
	    private String seo_title;//应用SEO标题
	    private String seo_keywords;//应用SEO关键字
	    private String seo_desc;//应用SEO描述
		public String getApp_id() {
			return app_id;
		}
		public void setApp_id(String app_id) {
			this.app_id = app_id;
		}
		public String getLast_cate_id() {
			return last_cate_id;
		}
		public void setLast_cate_id(String last_cate_id) {
			this.last_cate_id = last_cate_id;
		}
		public String getApp_title() {
			return app_title;
		}
		public void setApp_title(String app_title) {
			this.app_title = app_title;
		}
		public String getApp_stitle() {
			return app_stitle;
		}
		public void setApp_stitle(String app_stitle) {
			this.app_stitle = app_stitle;
		}
		public String getApp_version() {
			return app_version;
		}
		public void setApp_version(String app_version) {
			this.app_version = app_version;
		}
		public String getApp_update_time() {
//			if (!"".equals(app_update_time)) {
//				return TimeUtil.longToDateStr(Long.parseLong(app_update_time),"yyyy-MM-dd");
//			}else{
//				return "";
//			}
			return app_update_time;
		}
		public void setApp_update_time(String app_update_time) {
			this.app_update_time = app_update_time;
		}
		public String getApp_size() {
			return app_size;
		}
		public void setApp_size(String app_size) {
			this.app_size = app_size;
		}
		public String getApp_system() {
			return app_system;
		}
		public void setApp_system(String app_system) {
			this.app_system = app_system;
		}
		public String getApp_type() {
			return app_type;
		}
		public void setApp_type(String app_type) {
			this.app_type = app_type;
		}
		public String getApp_logo() {
			if (!VerifyUtils.isUrl(app_logo)) {
				app_logo = Constance.BASE_URL+app_logo;
			}
			return app_logo;
		}
		public void setApp_logo(String app_logo) {
			this.app_logo = app_logo;
		}
		public String getApp_desc() {
			return app_desc;
		}
		public void setApp_desc(String app_desc) {
			this.app_desc = app_desc;
		}
		public String getApp_company() {
			return app_company;
		}
		public void setApp_company(String app_company) {
			this.app_company = app_company;
		}
		public String getApp_company_url() {
			return app_company_url;
		}
		public void setApp_company_url(String app_company_url) {
			this.app_company_url = app_company_url;
		}
		public String getApp_tags() {
			return app_tags;
		}
		public void setApp_tags(String app_tags) {
			this.app_tags = app_tags;
		}
		public String getCreate_time() {
			return create_time;
		}
		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}
		public String getApp_grade() {
			return app_grade;
		}
		public void setApp_grade(String app_grade) {
			this.app_grade = app_grade;
		}
		public String getApp_recomment() {
			return app_recomment;
		}
		public void setApp_recomment(String app_recomment) {
			this.app_recomment = app_recomment;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public String getApp_comments() {
			return app_comments;
		}
		public void setApp_comments(String app_comments) {
			this.app_comments = app_comments;
		}
		public String getApp_visitors() {
			return app_visitors;
		}
		public void setApp_visitors(String app_visitors) {
			this.app_visitors = app_visitors;
		}
		public String getApp_down() {
			return app_down;
		}
		public void setApp_down(String app_down) {
			this.app_down = app_down;
		}
		public String getApp_order() {
			return app_order;
		}
		public void setApp_order(String app_order) {
			this.app_order = app_order;
		}
		public String getData_app_id() {
			return data_app_id;
		}
		public void setData_app_id(String data_app_id) {
			this.data_app_id = data_app_id;
		}
		public String getCharge_app_id() {
			return charge_app_id;
		}
		public void setCharge_app_id(String charge_app_id) {
			this.charge_app_id = charge_app_id;
		}
		public String getRewrite_tag() {
			return rewrite_tag;
		}
		public void setRewrite_tag(String rewrite_tag) {
			this.rewrite_tag = rewrite_tag;
		}
		public String getSeo_title() {
			return seo_title;
		}
		public void setSeo_title(String seo_title) {
			this.seo_title = seo_title;
		}
		public String getSeo_keywords() {
			return seo_keywords;
		}
		public void setSeo_keywords(String seo_keywords) {
			this.seo_keywords = seo_keywords;
		}
		public String getSeo_desc() {
			return seo_desc;
		}
		public void setSeo_desc(String seo_desc) {
			this.seo_desc = seo_desc;
		}
		public App(String app_id, String last_cate_id, String app_title,
				String app_stitle, String app_version, String app_update_time,
				String app_size, String app_system, String app_type,
				String app_logo, String app_desc, String app_company,
				String app_company_url, String app_tags, String create_time,
				String app_grade, String app_recomment, String uid,
				String uname, String app_comments, String app_visitors,
				String app_down, String app_order, String data_app_id,
				String charge_app_id, String rewrite_tag, String seo_title,
				String seo_keywords, String seo_desc) {
			super();
			this.app_id = app_id;
			this.last_cate_id = last_cate_id;
			this.app_title = app_title;
			this.app_stitle = app_stitle;
			this.app_version = app_version;
			this.app_update_time = app_update_time;
			this.app_size = app_size;
			this.app_system = app_system;
			this.app_type = app_type;
			this.app_logo = app_logo;
			this.app_desc = app_desc;
			this.app_company = app_company;
			this.app_company_url = app_company_url;
			this.app_tags = app_tags;
			this.create_time = create_time;
			this.app_grade = app_grade;
			this.app_recomment = app_recomment;
			this.uid = uid;
			this.uname = uname;
			this.app_comments = app_comments;
			this.app_visitors = app_visitors;
			this.app_down = app_down;
			this.app_order = app_order;
			this.data_app_id = data_app_id;
			this.charge_app_id = charge_app_id;
			this.rewrite_tag = rewrite_tag;
			this.seo_title = seo_title;
			this.seo_keywords = seo_keywords;
			this.seo_desc = seo_desc;
		}
		public App() {
			super();
		}
	    
	    
}
