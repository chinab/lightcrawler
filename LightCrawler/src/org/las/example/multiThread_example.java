package org.las.example;

import org.las.crawler.Controller;
import org.las.crawler.PageEntity;

public class multiThread_example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Controller controller = new Controller("http://www.amazon.cn/", 3){
			
			protected void handlePage(final PageEntity page){
				//You can get information from here
				page.print();
			}
		};
		controller.start(5);
	}

}
