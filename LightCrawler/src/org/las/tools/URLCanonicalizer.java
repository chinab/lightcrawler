package org.las.tools;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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

	public static URL getCanonicalURL(String baseURI, String relativePath) {
		
		//remove self link
		if (relativePath.contains("#")) {
			relativePath = relativePath.substring(0, relativePath.indexOf("#"));
        }
		//format url
		if(relativePath.lastIndexOf('/')>=0 && relativePath.lastIndexOf('/') == relativePath.length()-1){
			relativePath = relativePath.substring(0, relativePath.length()-1);
		}
		//build full href with context
        try {
        	if (baseURI == null) {
        		return new URL(relativePath);
        	} else {
        		if(relativePath.indexOf('?')==0){
        			if(baseURI.indexOf('?')>=0){
        				return new URL(baseURI.substring(0,baseURI.indexOf('?'))+relativePath);
        			}else{
        				return new URL(baseURI+relativePath);
        			}
        		}else{
        			return new URL(new URL(baseURI), relativePath);
        		}
        	}
        } catch (MalformedURLException ex) {
            return null;
        }
        
	}
	
	public static URL getAbsoluteURL(String baseURI, String relativePath) {
		URL absURL = null;
		try {
			URI base = new URI(baseURI);
			URI abs = base.resolve(relativePath);
			absURL = abs.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
		return absURL;
	}

}
