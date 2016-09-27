package com.main.model;

public class Admin {
	private int id;
	private String account;
	private String password;
	private int permission;//管理员权限，用整数表示，数字越大，权限越小，1为超级管理员
	private int state;//管理员账号的使用状态，0为正常使用，1为暂停使用，2为禁止使用
	private String nickName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Admin(int id, String account, String password, int permission,
			int state,String nickName) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.permission = permission;
		this.state = state;
		this.nickName = nickName;
	}
	public Admin() {
		super();
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", account=" + account + ", password="
				+ password + ", permission=" + permission + ", state=" + state
				+ ", nickName=" + nickName + "]";
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
