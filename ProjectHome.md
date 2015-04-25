## Light Crawler ##
An Open Source Crawler for Java. Feature of LightCrawler list down below:

  1. [LightCrawler](LightCrawler.md) can control the depth of the crawler. Crawler will stop at the pointed depth.
  1. [LightCrawler](LightCrawler.md) is also Multi-Threads, Easily and Quickly to Build.
  1. [LightCrawler](LightCrawler.md) can choose which url should be crawled and which should not be crawled by configing forbidden regex or allowed regex.
  1. [LightCrawler](LightCrawler.md) can judge RSS Feed or HTML and choose the right parser automaticaly.
  1. [LightCrawler](LightCrawler.md) fetcher can extract Title, Language, Encoding, ContentType, Md5, SimHash FingerPrint. That is important information for user.
  1. Fetch queue stored in memory. it's fast in writing and reading.


## Example ##
**Single thread use:**
```
String start_url = "http://www.###.com";
int depth = 3;
Crawler mycrawler = new Crawler(start_url,depth){
			
	protected void handlePage(final PageEntity page){
	//You can get information from here
		page.print();
	}
};
mycrawler.start();
```
**Attention:** The depth of  feed is 1, then the depth of  next crawl url is 2.

**Multi-thread use:**
input feed url and crawl depth
```
String start_url = "http://www.###.com";
int depth = 3;
int thread = 5;
Controller controller = new Controller(start_url, depth){
			
	protected void handlePage(final PageEntity page){
		//You can get information from here
		page.print();
	}
};
controller.start(thread);
```

**More than one feed url:**
```
List<String> url_list = new ArrayList<String>();
url_list.add("http://www.###1.com");
url_list.add("http://www.###2.com");
int depth = 3;
Crawler mycrawler = new Crawler(url_list, depth);
mycrawler.start();
```

**Control the Crawler:**
```
String start_url = "http://www.###.com";
List<String> forbidden_url_list = new ArrayList<String>();
forbidden_url_list.add(".*(\\.(jpg|rar|tar|mpeg|mpeg-4|avi|tiff|tiff?|jpe?g
|vmf|vdf|zip|flv|rmvb|rm|atom|bib|dtd|mov|mid|mp2|mp3|mp4|mpg|wma|wmv|m4v|svg
|swf|rv|gif|jpg|png|ico|css|sit|eps|gz|rmp|tgz|exe|jpeg|jpe|js|ram|ps|bmp))$");

List<String> allowed_url_list = new ArrayList<String>();
allowed_url_list.add("http://www.###.com/.+");

int depth = 3;
Crawler mycrawler = new Crawler(start_url, depth, forbidden_url_list, allowed_url_list);
mycrawler.start();
```
_forbidden\_url\_list_ said that crawler do not crawl the urls ending with .jpg, .rar etc.
_allowed\_url\_list_ said that crawler only crawl the urls like the pattern "http://www.###.com/.+" .
You can add more than one rules into _forbidden\_url\_list_ and _allowed\_url\_list_.


**Source Code**
> https://lightcrawler.googlecode.com/svn

