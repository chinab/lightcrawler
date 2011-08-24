package org.las.crawler;

import java.util.Date;

public class URLEntity {
	
	private int id;
	private String url;
	private String parent_url;
	private String anchor_text;
	private String title;
	private String discription;
	private Date publishData;
	private int depth;
	
	public URLEntity(){		
	}
	
	public URLEntity(String url){		
		this.url = url;
	}
	
	public URLEntity(String url, int depth){		
		this.url = url;
		this.depth = depth;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Date getPublishData() {
		return publishData;
	}
	public void setPublishData(Date publishData) {
		this.publishData = publishData;
	}
	private String suffix;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent_url() {
		return parent_url;
	}
	public void setParent_url(String parentUrl) {
		parent_url = parentUrl;
	}
	public String getAnchor_text() {
		return anchor_text;
	}
	public void setAnchor_text(String anchorText) {
		anchor_text = anchorText;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	

}
