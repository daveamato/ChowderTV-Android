package com.com.example.item;

public class ItemHome {
	
	private int id;
	private String ChannelUrl;
	private String Image;
	private String ChannelName;
	private String Description;
	
	
	public ItemHome() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemHome(int id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}
	
	public ItemHome(int id,String channelname,String image,String description) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.ChannelName=channelname;
		this.Image=image;
		this.Description=description;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getChannelUrl() {
		return ChannelUrl;
	}

	public void setChannelUrl(String url) {
		this.ChannelUrl = url;
	}
	
	
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		this.Image = image;
	}
	
	public String getChannelName() {
		return ChannelName;
	}

	public void setChannelName(String channelname) {
		this.ChannelName = channelname;
	}
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String desc) {
		this.Description = desc;
	}

}
