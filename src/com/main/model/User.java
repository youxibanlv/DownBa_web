package com.main.model;

public class User {

	private int uid;
	private String username;
	private String password;
	private String token;
	private String phone;
	private String nickname;
	private String icon;
	private String alipay;
	private String point;
	private long regist_time;// 注册时间
	private long update_time;// 更新时间

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public User(int uid, String username, String password, String token, String phone, String nickname, String icon,
			String alipay, String point) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.token = token;
		this.phone = phone;
		this.nickname = nickname;
		this.icon = icon;
		this.alipay = alipay;
		this.point = point;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", token=" + token
				+ ", phone=" + phone + ", nickname=" + nickname + ", icon=" + icon + ", alipay=" + alipay + ", point="
				+ point + "]";
	}

	public long getRegist_time() {
		return regist_time;
	}

	public void setRegist_time(long regist_time) {
		this.regist_time = regist_time;
	}

	public long getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}

}
