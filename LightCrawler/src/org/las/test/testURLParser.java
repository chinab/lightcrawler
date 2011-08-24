package org.las.test;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

public class testURLParser extends TestCase {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public void buildURL() throws MalformedURLException {

		String href = "http://www.nap.edu/catalog.php?record_id=13117";
		String context = "http://sites.nationalacademies.org/SSB/ssb_051650";
		URL url = new URL(new URL(context), href);
		System.out.println(url);
	}

}
