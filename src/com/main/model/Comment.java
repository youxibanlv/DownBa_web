package com.main.model;

public class Comment {
	private int id;
	private int type;
	private String content;
	private int date_add;
	private int uid;
	private String uname;
	private String ip;
	private String ip_addr;
	private int parent_id;
	private int is_check;
	private int son;
	private int good;
	private int bad;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDate_add() {
		return date_add;
	}
	public void setDate_add(int date_add) {
		this.date_add = date_add;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getIs_check() {
		return is_check;
	}
	public void setIs_check(int is_check) {
		this.is_check = is_check;
	}
	public int getSon() {
		return son;
	}
	public void setSon(int son) {
		this.son = son;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public Comment(int id, int type, String content, int date_add, int uid, String uname, String ip, String ip_addr,
			int parent_id, int is_check, int son, int good, int bad) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
		this.date_add = date_add;
		this.uid = uid;
		this.uname = uname;
		this.ip = ip;
		this.ip_addr = ip_addr;
		this.parent_id = parent_id;
		this.is_check = is_check;
		this.son = son;
		this.good = good;
		this.bad = bad;
	}
	public Comment() {
		super();
	}
	
	
}
