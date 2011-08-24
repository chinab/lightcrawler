package org.las.crawler;

import it.unimi.dsi.parser.BulletParser;
import it.unimi.dsi.parser.callback.TextExtractor;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.las.tools.*;
import org.las.tools.FingerPrinter.FingerPrinter;
import org.las.tools.LanguageIdentifier.LanguageIdentifier;


public class HtmlParser {

	private BulletParser bulletParser;
	private TextExtractor textExtractor;
	private LinkExtractor linkExtractor;
	private LanguageIdentifier langIdentifier;

	private static final int MAX_OUT_LINKS = Config.getIntProperty(
			"fetcher.max_outlinks", 500);

	public HtmlParser() {
		bulletParser = new BulletParser();
		textExtractor = new TextExtractor();
		linkExtractor = new LinkExtractor();
		langIdentifier = new LanguageIdentifier();
	}

	public Set<URLEntity> parse(PageEntity page) {
		
		Set<URLEntity> links = new HashSet<URLEntity>();
		String encode = page.getEncode();
		if(encode==null){
			encode = "UTF-8";
		}
		if(!encode.equalsIgnoreCase("GBK")&&!encode.equalsIgnoreCase("GB2312")&&!encode.equalsIgnoreCase("ISO-8859-1")){
			encode = "UTF-8";
		}
		char[] chars = null;
		try {
			chars = new String(page.getContent(),encode).toCharArray();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bulletParser.setCallback(textExtractor);
		bulletParser.parse(chars);
		String text = textExtractor.text.toString().trim();
		String htmltext = null;
		try {
			htmltext = new String(page.getContent(),encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String title = textExtractor.title.toString().trim();
		String lang = langIdentifier.identify(text);
		long fingerprint = FingerPrinter.getLongCode(text);
				
		if(page.getTitle()==null){
			page.setTitle(title);
		}
		page.setParseText(text);
		page.setParseHtmlText(htmltext);
		page.setLang(lang);
		page.setFingerPrint(fingerprint);

		bulletParser.setCallback(linkExtractor);
		bulletParser.parse(chars);

		int urlCount = 0;
		for (URLEntity link : linkExtractor.getLinks()) {
			String href = link.getUrl();
			href = href.trim();
			if (href.length() == 0) {
				continue;
			}
			if (href.indexOf("javascript:") < 0
					&& href.indexOf("@") < 0) {
				URL url = URLCanonicalizer.getCanonicalURL(href, page.getUrl());
				if (url != null) {
					link.setUrl(url.toExternalForm());
					link.setParent_url(page.getUrl());
					link.setSuffix(Formater.JudgeURLFormat(link.getUrl()));
					links.add(link);
					urlCount++;
					if (urlCount > MAX_OUT_LINKS) {
						break;
					}	
				}				
			}
		}
		
		return links;
	}

}
