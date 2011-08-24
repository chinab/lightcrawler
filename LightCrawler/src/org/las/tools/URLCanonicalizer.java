package org.las.tools;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright (C) 2010.
 * 
 * @author xie
 */


public final class URLCanonicalizer {

	public static String getCanonicalURL(String url) {
		URL canonicalURL = getCanonicalURL(url, null);
		if (canonicalURL != null) {
			return canonicalURL.toExternalForm();
		}
		return null;
	}

	public static URL getCanonicalURL(String href, String context) {
		
		//remove self link
		if (href.contains("#")) {
            href = href.substring(0, href.indexOf("#"));
        }
		//format url
		if(href.lastIndexOf('/')>=0 && href.lastIndexOf('/') == href.length()-1){
			href = href.substring(0, href.length()-1);
		}
		//build full href with context
        try {
        	if (context == null) {
        		return new URL(href);
        	} else {
        		if(href.indexOf('?')==0){
        			if(context.indexOf('?')>=0){
        				return new URL(context.substring(0,context.indexOf('?'))+href);
        			}else{
        				return new URL(context+href);
        			}
        		}else{
        			return new URL(new URL(context), href);
        		}
        	}
        } catch (MalformedURLException ex) {
            return null;
        }
        
	}
}
