package com.main.model;



import java.io.Serializable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.main.utils.Constance;
import com.main.utils.TimeUtil;
import com.main.utils.VerifyUtils;

/**
 * Created by strike on 16/8/4.
 */
public class Info implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int info_id;
    private int parent_id;
    private int last_cate_id;
    private String info_title;
    private String info_img;
    private String info_body;
    private int info_visitors;
    private String info_desc;
    private String info_update_time;
    private String info_from;

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getLast_cate_id() {
        return last_cate_id;
    }

    public void setLast_cate_id(int last_cate_id) {
        this.last_cate_id = last_cate_id;
    }

    public String getInfo_title() {
        return info_title;
    }

    public void setInfo_title(String info_title) {
        this.info_title = info_title;
    }

    public String getInfo_img() {
        return info_img;
    }

    public void setInfo_img(String info_img) {
        this.info_img = info_img;
    }

    public String getInfo_body() {
    	//替换相对路径为绝对路径
    	Document document = Jsoup.parse(info_body);
    	Elements imgs = document.select("img[src]");
    	for(Element img :imgs){
    		String imgUrl = img.attr("src");
    		if (!VerifyUtils.isUrl(imgUrl)) {
				imgUrl = Constance.BASE_IMG_URL + imgUrl;
				img.attr("src",imgUrl);
			}
    	}
        return document.toString();
    }

    public void setInfo_body(String info_body) {
        this.info_body = info_body;
    }

    public int getInfo_visitors() {
        return info_visitors;
    }

    public void setInfo_visitors(int info_visitors) {
        this.info_visitors = info_visitors;
    }

	public String getInfo_desc() {
		return info_desc;
	}

	public void setInfo_desc(String info_desc) {
		this.info_desc = info_desc;
	}

	public String getInfo_update_time() {
		return TimeUtil.longToDateStr(Long.parseLong(info_update_time)*1000, "yyyy年MM月dd日 hh:mm:ss");
	}

	public void setInfo_update_time(String info_update_time) {
		this.info_update_time = info_update_time;
	}

	public String getInfo_from() {
		return info_from;
	}

	public void setInfo_from(String info_from) {
		this.info_from = info_from;
	}
}
