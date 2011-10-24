package org.las.crawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Crawler extends Thread{

	private Controller controller;
	private FetchQueue queue;
	private HtmlParser htmlparser;
	private FeedParser feedparser;
	private Fetcher fetcher;
	
	private final int crawl_depth;
	private final List<Pattern> forbidden_patterns;
	private final List<Pattern> allowed_patterns;
	
	public Crawler(String seed, int depth){
		this.crawl_depth = depth;
		this.queue = new FetchQueue();
		this.fetcher = new Fetcher();
		this.htmlparser =  new HtmlParser();
		this.feedparser = new FeedParser();
		this.forbidden_patterns = new ArrayList<Pattern>();
		this.allowed_patterns = new ArrayList<Pattern>();
		if(seed!=null){
			URLEntity urlEntity = new URLEntity(seed, 1);
			queue.enQueue(urlEntity);
		}
		String forbidden_regex_str = Config.getStringProperty("crawler.url_forbidden_regex", "");
		String allow_regex_str = Config.getStringProperty("crawler.url_allowed_regex", "");
		if(forbidden_regex_str!=null){
			for(String forbidden_regex : forbidden_regex_str.split(";")){
				forbidden_patterns.add(Pattern.compile(forbidden_regex));
			}
		}
		if(allow_regex_str!=null){
			for(String allowed_regex : allow_regex_str.split(";")){
				allowed_patterns.add(Pattern.compile(allowed_regex));
			}
		}
	}
	
	public Crawler(List<String> seedList, int depth){
		this.crawl_depth = depth;
		this.queue = new FetchQueue();
		this.fetcher = new Fetcher();
		this.htmlparser =  new HtmlParser();
		this.feedparser = new FeedParser();
		this.forbidden_patterns = new ArrayList<Pattern>();
		this.allowed_patterns = new ArrayList<Pattern>();
		if(seedList!=null){
			for(String seed:seedList){
				URLEntity urlEntity = new URLEntity(seed, 1);
				queue.enQueue(urlEntity);
			}
		}
		String forbidden_regex_str = Config.getStringProperty("crawler.url_forbidden_regex", "");
		String allow_regex_str = Config.getStringProperty("crawler.url_allowed_regex", "");
		if(forbidden_regex_str!=null){
			for(String forbidden_regex : forbidden_regex_str.split(";")){
				forbidden_patterns.add(Pattern.compile(forbidden_regex));
			}
		}
		if(allow_regex_str!=null){
			for(String allowed_regex : allow_regex_str.split(";")){
				allowed_patterns.add(Pattern.compile(allowed_regex));
			}
		}
	}
	
	public Crawler(String seed, int depth, List<String> forbidden_regex_list, List<String> allowed_regex_list){
		this.crawl_depth = depth;
		this.queue = new FetchQueue();
		this.fetcher = new Fetcher();
		this.htmlparser =  new HtmlParser();
		this.feedparser = new FeedParser();
		this.forbidden_patterns = new ArrayList<Pattern>();
		this.allowed_patterns = new ArrayList<Pattern>();
		if(seed!=null){
			URLEntity urlEntity = new URLEntity(seed, 1);
			queue.enQueue(urlEntity);
		}
		if(forbidden_regex_list!=null){
			for(String forbidden_regex:forbidden_regex_list){
				forbidden_patterns.add(Pattern.compile(forbidden_regex));
			}
		}
		if(allowed_regex_list!=null){
			for(String allowed_regex:allowed_regex_list){
				allowed_patterns.add(Pattern.compile(allowed_regex));
			}
		}
	}
	
	public Crawler(List<String> seedList, int depth, List<String> forbidden_regex_list, List<String> allowed_regex_list){
		this.crawl_depth = depth;
		this.queue = new FetchQueue();
		this.fetcher = new Fetcher();
		this.htmlparser =  new HtmlParser();
		this.feedparser = new FeedParser();
		this.forbidden_patterns = new ArrayList<Pattern>();
		this.allowed_patterns = new ArrayList<Pattern>();
		if(seedList!=null){
			for(String seed:seedList){
				URLEntity urlEntity = new URLEntity(seed, 1);
				queue.enQueue(urlEntity);
			}
		}
		if(forbidden_regex_list!=null){
			for(String forbidden_regex:forbidden_regex_list){
				forbidden_patterns.add(Pattern.compile(forbidden_regex));
			}
		}
		if(allowed_regex_list!=null){
			for(String allowed_regex:allowed_regex_list){
				allowed_patterns.add(Pattern.compile(allowed_regex));
			}
		}
	}
	
	public Crawler(Controller controller){
		this.controller = controller;
		this.queue = controller.getQueue();
		this.crawl_depth = controller.getCrawl_depth();
		this.fetcher = new Fetcher();
		this.htmlparser =  new HtmlParser();
		this.feedparser = new FeedParser();
		this.forbidden_patterns = controller.getForbidden_patterns();
		this.allowed_patterns = controller.getAllowed_patterns();
	}
	
	public void run() {
		while (true) {
			URLEntity fetch_urlEntity = queue.deQueue();
			if (fetch_urlEntity == null) {
				if (controller != null) {
					signal = 1;
					if(controller.isFinish()){
						break;
					}else{
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
				} else {
					break;
				}
			} else {
				signal = 0;
				if (fetch_urlEntity.getDepth() > current_depth) {
					// controller.waitingDepthJump();
					current_depth++;
				}
				PageEntity page = new PageEntity();
				int status_code = fetcher.fetch(fetch_urlEntity, page);
				if (status_code == Fetcher.OK) {
					System.out.println(">>>>>Depth = "
							+ fetch_urlEntity.getDepth() + " <<<<<<<<<<");
					String format = page.getFormat();
					if (format != null) {
						Set<URLEntity> links = new HashSet<URLEntity>();
						if (format.indexOf("html") > -1) {
							links = htmlparser.parse(page);
						}
						if (format.indexOf("xml") > -1) {
							links = feedparser.parse(page);
						}

						if (fetch_urlEntity.getDepth() + 1 <= crawl_depth) {
							for (URLEntity link : links) {
								String url = link.getUrl();
								if (shouldCrawl(url)) {
									link
											.setDepth(fetch_urlEntity
													.getDepth() + 1);
									queue.enQueue(link);
								}
							}
						}
					}
					if(controller!=null){
						controller.handlePage(page);
					}else{
						handlePage(page);
					}
					System.out.println("Queue Status =  "
							+ (queue.size() - queue.length()) + " / "
							+ queue.size());
				} else {
					System.out.println(fetch_urlEntity.getUrl());
					System.out.println(Fetcher.statusCodesToString(status_code));
				}
				// Politeness delay
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected boolean shouldCrawl(final String url){
		if(forbidden_patterns != null){
			for(Pattern forbidden_patten:forbidden_patterns){
				if (forbidden_patten.matcher(url).matches()) {
					return false;
				}
			}
		}
		if(allowed_patterns != null){
			for(Pattern allowed_patten:allowed_patterns){
				if (!allowed_patten.matcher(url).matches()) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected void handlePage(final PageEntity page){
		page.print();
	}
	
	public int getSignal() {
		return signal;
	}

	private int current_depth = 0;
	private int signal = 0;
}
