package com.main.model;

public class Channel {

	private int id;
	private String channel_id;
	private String channel_name;
	private String bak;
	private int downloads;
	private long update_time;
	private String dateTime;
	public Channel( int id, String channel_id, String channel_name, String bak, int downloads,long update_time) {
		super();
		this.id = id;
		this.channel_id = channel_id;
		this.channel_name = channel_name;
		this.bak = bak;
		this.downloads = downloads;
		this.update_time = update_time;
	}
	public Channel() {
		super();
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getBak() {
		return bak;
	}
	public void setBak(String bak) {
		this.bak = bak;
	}
	public int getDownloads() {
		return downloads;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	public long getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	
}
