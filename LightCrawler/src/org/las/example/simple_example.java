package org.las.example;

import org.las.crawler.Crawler;
import org.las.crawler.PageEntity;

public class simple_example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Crawler mycrawler = new Crawler("http://www.whitehouse.gov/",3){
			
			protected void handlePage(final PageEntity page){
				//You can get information from here
				page.print();
			}
		};
		mycrawler.start();
	}

}
