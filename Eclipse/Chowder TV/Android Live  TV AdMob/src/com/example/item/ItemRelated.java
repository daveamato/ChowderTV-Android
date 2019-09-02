package com.example.item;

public class ItemRelated {
	
	private String rel_id;
	private String rel_title;
	private String rel_v_image;
	
	public String getRelatedId() {
		return rel_id;
	}

	public void setRelatedId(String id) {
		this.rel_id = id;
	}

	
	public String getRelatedTitle() {
		return rel_title;
	}

	public void setRelatedTitle(String title) {
		this.rel_title = title;
	}
	
	public String getRelatedImage() {
		return rel_v_image;
	}

	public void setRelatedImage(String videoimage) {
		this.rel_v_image = videoimage;
	}
}
