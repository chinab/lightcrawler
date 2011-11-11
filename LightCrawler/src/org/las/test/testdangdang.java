package org.las.test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.las.crawler.FetchQueue;
import org.las.crawler.Fetcher;
import org.las.crawler.HtmlParser;
import org.las.crawler.FeedParser;
import org.las.crawler.PageEntity;
import org.las.crawler.URLEntity;


public class testdangdang extends TestCase {

	
static Pattern filters = Pattern.compile(".*(\\.(jpg|rar|tar|mpeg|mpeg-4|avi|tiff|tiff?|jpe?g|vmf|vdf|zip|flv|rmvb|rm|atom|bib|dtd|mov|mid|mp2|mp3|mp4|mpg|wma|wmv|m4v|svg|swf|rv|gif|jpg|png|ico|css|sit|eps|gz|rmp|tgz|exe|jpeg|jpe|js|ram|ps|bmp))$");
	
	/**
	 * @param args
	 */
	public void testDangDang() {
		// TODO Auto-generated method stub
		boolean n1=false;
		boolean n2=false;
		//boolean n3=false;
		
		
		// init
		//初始化抓取队列
		FetchQueue queue = new FetchQueue();
		
		//建立起始URL，将其放入队列
		URLEntity start_urlEntity = new URLEntity();
		String start_url = "http://list.dangdang.com/book/01.htm?ref=book-01-A";
		start_urlEntity.setUrl(start_url);
		start_urlEntity.setDepth(1);
		queue.enQueue(start_urlEntity);
		
		//初始化抓取器
		Fetcher fetcher = new Fetcher();
		
		//初始化解析器
		HtmlParser htmlparser = new HtmlParser();
		FeedParser feedparser = new FeedParser();
		
		//设置抓取深度
		int crawl_depth = 4;
		
		//设置抓取限定的URL类型（正则表达式表示）
		String url_filter1 = "http://list.dangdang.com/book/01.[0-9]{1,2}_Z[0-9]{1,2}.html\\?filter=0_0_0&ref=book-02-C";
		String url_filter2 = "http://list.dangdang.com/book/01.[0-9]{1,2}.[0-9]{1,2}_Z[0-9]{1,2}.html\\?filter=0_0_0&ref=book-02-C";
		String url_filter3 = "http://product.dangdang.com/product.aspx\\?product_id=[0-9]{1,8}&ref=book.+";
		
		

		//爬行过程（直到把所有队列的URL抓取完成为止）
		while (queue.length() > 0) {
			
			//出于礼貌的抓取延迟500ms
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//从队列头取出抓取URL
			URLEntity fetch_urlEntity = queue.deQueue();
			PageEntity page = new PageEntity();
			
			//抓取页面
			int status_code = fetcher.fetch(fetch_urlEntity, page);
			if (status_code == Fetcher.OK) {
				System.out.println(">>>>>Depth = "+ fetch_urlEntity.getDepth() + " <<<<<<<<<<");
				String type = page.getType();
				//根据页面类型确定是否要解析页面（如：富文档等不需要解析）
				if (type != null) {
					
					//根据页面类型调用不同的解析器
					Set<URLEntity> links = new HashSet<URLEntity>();
					if(type.indexOf("text/html") > -1){
						links = htmlparser.parse(page);
					}
					if(type.indexOf("text/xml") > -1){
						links = feedparser.parse(page);
					}
					
					
					//解析后，判断url是否加入队列进行深层爬取
					if (fetch_urlEntity.getDepth() + 1 <= crawl_depth) {
						for (URLEntity link : links) {
							// filter url
							String url = link.getUrl();
							//url后缀判断
							if (!filters.matcher(url.toLowerCase()).matches()) {
								//自定义的url类型判断
								if (fetch_urlEntity.getDepth()==1 && url.matches(url_filter1)) {
									if(!n1){
									link.setDepth(fetch_urlEntity.getDepth() + 1);
									queue.enQueue(link);
									n1=true;
									}
								}
								if (fetch_urlEntity.getDepth()==2 && url.matches(url_filter2)) {
									if(!n2){
									link.setDepth(fetch_urlEntity.getDepth() + 1);
									queue.enQueue(link);
									n2=true;
									}
								}
								if (fetch_urlEntity.getDepth()>=3 && url.matches(url_filter3)) {
									link.setDepth(fetch_urlEntity.getDepth() + 1);
									queue.enQueue(link);
								}
								/*
								if(fetch_urlEntity.getDepth()>=3){
									if(url.matches(url_filter3)){
										System.out.println("[true]:"+url);
									}else{
										System.out.println("[false]:"+url);
									}
								}
								*/
							}
						}
					}
				}
				
				//page.print();//输出抓取页面内容
				System.out.println("Queue Status =  "+(queue.size()-queue.length())+" / "+queue.size());
			
				if(fetch_urlEntity.getDepth()==4){
					page.print();
				}
				
			} else {
				System.out.println(Fetcher.statusCodesToString(status_code));
			}
		}
		
		//finish
		System.out.println();
		System.out.println("Crawl Finished!");
	}
}
