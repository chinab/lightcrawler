package org.las.crawler;

import java.util.*;

public class PageEntity {

	private int id;
	private String url;
	private String title;
	private String type;
	private String format;
	private String encode;
	private long size;
	private String digest;
	private byte[] content;
	private String anchorText;
	private String discription;
	private String extractText;
	private String extractHtml;
	private String lang;
	private long fingerPrint;
	private Date publishData;
	private Date downloadDate;
	private Set<String> links;
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getAnchorText() {
		return anchorText;
	}
	public void setAnchorText(String anchorText) {
		this.anchorText = anchorText;
	}
	public String getExtractText() {
		return extractText;
	}
	public void setExtractText(String extractText) {
		this.extractText = extractText;
	}
	public String getExtractHtml() {
		return extractHtml;
	}
	public void setExtractHtml(String extractHtml) {
		this.extractHtml = extractHtml;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public Date getPublishData() {
		return publishData;
	}
	public void setPublishData(Date publishData) {
		this.publishData = publishData;
	}
	public Date getDownloadDate() {
		return downloadDate;
	}
	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		this.links = links;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public long getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(long fingerPrint) {
		this.fingerPrint = fingerPrint;
	}	
		
	public void print(){
		System.out.println();
		System.out.println("---------------------------------");
		for(java.lang.reflect.Field field:this.getClass().getDeclaredFields()){
			try {
				String key = field.getName();
				String value = null;
				if(field.get(this)!=null){
					value = field.get(this).toString();
					if(value.length()>500){
						value = value.substring(0,500);
					}
					value = value.replaceAll("\n", "");
					value = value.replaceAll("\r", "");
				}
				System.out.println(key+'='+value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		//System.out.println("content : ");
		//System.out.println(new String(content));
		System.out.println("---------------------------------");
		System.out.println();
	}

}
