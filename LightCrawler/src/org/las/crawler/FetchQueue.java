package org.las.crawler;

import java.util.*;

public class FetchQueue {
	
	private Map<String, URLEntity> urlMap = new HashMap<String, URLEntity>();
	private LinkedList<URLEntity> queue = new LinkedList<URLEntity>();
	private int cursor = 0;
	private Object mutex = new Object();
	
	
	
	public void enQueue(URLEntity urlEntity){
		String url = urlEntity.getUrl();
		synchronized (mutex) {
			if(!urlMap.containsKey(url)){
				urlMap.put(url, urlEntity);
				queue.addLast(urlEntity);
			}
		}
	}
	
	public void enQueue(Collection<URLEntity> urlEntitys) {
		for (URLEntity urlEntity : urlEntitys) {
			String url = urlEntity.getUrl();
			synchronized (mutex) {
				if (!urlMap.containsKey(url)) {
					urlMap.put(url, urlEntity);
					queue.addLast(urlEntity);
				}
			}
		}
	}
	
	public URLEntity deQueue(){
		synchronized(mutex){
			if(cursor>=queue.size()){
				return null;
			}else{
				URLEntity url = queue.get(cursor);
				cursor++;
				return url;
			}
		}
	}
	
	public void jumpQueue(URLEntity urlEntity){
		String url = urlEntity.getUrl();
		synchronized (mutex) {
			if(!urlMap.containsKey(url)){
				urlMap.put(url, urlEntity);
				queue.addFirst(urlEntity);
			}
		}
	}
	
	public int length(){
		synchronized(mutex){
			return queue.size()-cursor;
		}
	}
	
	public int size(){
		synchronized(mutex){
			return queue.size();
		}
	}

}
