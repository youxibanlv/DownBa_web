package com.main.utils;

public interface Constance {
	//默认单页显示的数据条数
	int DEFALT_PAGESIZE = 10;
	
	//图片资源的外网ip
	String BASE_URL = "http://123.57.86.113";//外网
	
	String BASE_IMG_URL = "http://www.82down.com";
	
	int DEFAULT_LENGTH = 20;//默认标题长度
	
	int TYPE_WHEEL = 1;//1为轮播图
	
	int TYPW_SUSPECT = 2;//2为猜你喜欢
	
	int ORDER_HOT= 1;//排行
    int ORDER_NEW = 2;//最新
    
    int cate_original = 23;//原创的数据库id
    int cate_review = 19;//评测的数据库id
    int cate_news = 22;//行业新闻的数据库id
    int cate_app = 1;//应用
    int cate_game= 2;//游戏
	
	 String CLIENT_SECRET ="down8app@strike%*xo" ;//app鉴权秘钥
}
