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

public class testCrawler extends TestCase {

	
static Pattern filters = Pattern.compile(".*(\\.(jpg|rar|tar|mpeg|mpeg-4|avi|tiff|tiff?|jpe?g|vmf|vdf|zip|flv|rmvb|rm|atom|bib|dtd|mov|mid|mp2|mp3|mp4|mpg|wma|wmv|m4v|svg|swf|rv|gif|jpg|png|ico|css|sit|eps|gz|rmp|tgz|exe|jpeg|jpe|js|ram|ps|bmp))$");
	
	/**
	 * @param args
	 */
	public void test() {
		
		// init
		FetchQueue queue = new FetchQueue();
		URLEntity start_urlEntity = new URLEntity();
		String start_url = "http://web.mit.edu/mitei/news/index.html";
		String url_filter = "http://web.mit.edu/newsoffice/[0-9]*/.+";
		//String start_url = "http://www.sciencebusiness.net/RssFeeds.aspx?TypeId=6";
		//String url_filter = ".+";
		start_urlEntity.setUrl(start_url);
		start_urlEntity.setDepth(1);
		queue.enQueue(start_urlEntity);
		Fetcher fetcher = new Fetcher();
		HtmlParser htmlparser = new HtmlParser();
		FeedParser feedparser = new FeedParser();
		int crawl_depth = 2;

		// crawl
		while (queue.length() > 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			URLEntity fetch_urlEntity = queue.deQueue();
			PageEntity page = new PageEntity();
			int status_code = fetcher.fetch(fetch_urlEntity, page);
			if (status_code == Fetcher.OK) {
				System.out.println(">>>>>Depth = "+ fetch_urlEntity.getDepth() + " <<<<<<<<<<");
				String type = page.getType();
				if (type != null) {
					Set<URLEntity> links = new HashSet<URLEntity>();
					if(type.indexOf("html") > -1){
						links = htmlparser.parse(page);
					}
					if(type.indexOf("xml") > -1){
						links = feedparser.parse(page);
					}
					
					if (fetch_urlEntity.getDepth() + 1 <= crawl_depth) {
						for (URLEntity link : links) {
							// filter url
							String url = link.getUrl();
							System.out.println(url);
							if (!filters.matcher(url.toLowerCase()).matches()) {
								if (url.matches(url_filter)) {
									//System.out.println(url);
									link.setDepth(fetch_urlEntity.getDepth() + 1);
									queue.enQueue(link);
								}
							}
						}
					}
				}
				page.print();
				System.out.println("Queue Status =  "+(queue.size()-queue.length())+" / "+queue.size());
			} else {
				System.out.println(Fetcher.statusCodesToString(status_code));
			}
		}
		
		//finish
		System.out.println();
		System.out.println("Crawl Finished!");
	}
}
