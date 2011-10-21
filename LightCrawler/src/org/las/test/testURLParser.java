package org.las.test;

import java.net.MalformedURLException;

import org.las.tools.URLCanonicalizer;

import junit.framework.TestCase;

public class testURLParser extends TestCase {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public void testBuildURL() throws MalformedURLException {

		String href = "?record_id=13117";
		String context = "http://www.nap.edu/new/age/cab";
		System.out.println(URLCanonicalizer.getCanonicalURL(href,context));
		System.out.println(URLCanonicalizer.getAbsoluteURL(context, href));
	}

}
