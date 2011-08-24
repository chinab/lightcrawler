package org.las.test;

import junit.framework.TestCase;

import org.las.crawler.*;

public class TestMemQueue extends TestCase {

	/**
	 * @param args
	 */
	public void addQueue() {

		FetchQueue queue = new FetchQueue();
		int count =0;
		long begin = Runtime.getRuntime().freeMemory();
		while(count<10000){
			System.out.println(++count);
			URLEntity urlEntity = new URLEntity();
			urlEntity.setUrl("http://difjeklkdjfi.jcomfdki.ajdiojfakljdkljfjiekldjaklf?conmfdsl=fuelaijdkf+&difjl="+count);
			urlEntity.setAnchor_text("这里是标题标题标题");
			urlEntity.setDepth(3);
			urlEntity.setParent_url("http://www.las.ac.cn");
			urlEntity.setSuffix("html");
			queue.enQueue(urlEntity);
		}
		long end = Runtime.getRuntime().freeMemory();
		System.out.println("Memory comsume: "+(begin-end)/1024+"K");
		
	}

}
