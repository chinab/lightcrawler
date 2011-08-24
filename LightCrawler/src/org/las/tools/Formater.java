package org.las.tools;



public class Formater {


	public static String JudgeURLFormat(String url){
		if(url==null||url.indexOf('.')<0){
			return null;
		}
		int dot_cursor = url.lastIndexOf('.')+1;
		int suffix_length = url.length()-dot_cursor;
		if(suffix_length > 0 && suffix_length <= 4 ){
			return url.substring(dot_cursor, url.length()).toLowerCase().trim();
		}else{
			return "/";
		}
	}
	
	public static String JudgeHttpFormat(String url, String contentType) {
		String type = null;
		if(contentType!=null){
			type = contentType.toLowerCase();
			if(type.indexOf("text/html")>-1){
				return type="html";
			}else if(type.indexOf("application/msword")>-1){
				type="doc";
			}else if((type.indexOf("application/pdf")>-1)){
				type="pdf";
			}else if(type.indexOf("application/pics-rules")>-1){
				type="pdf";
			}else if(type.indexOf("application/octet-stream")>-1){
				type="csv";
			}else if(type.indexOf("text/plain")>-1){
				type="txt";
			}else if(type.indexOf("application/vnd.ms-powerpoint")>-1){
				type="ppt";
			}else if(type.indexOf("application/vnd.ms-excel")>-1){				                      
				type="xls";
			}else if(type.indexOf("application/rtf")>-1){				                      
				type="rtf";
			}else if(type.indexOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")>-1){				                      
				type="xlsx";
			}else {
				type="html";
			}
		}else{
			url = url.toLowerCase();
			if(url.lastIndexOf(".doc")==url.length()-4){
				type="doc";
			}else if(url.lastIndexOf(".jsp")==url.length()-4){
				type="html";
			}else if(url.lastIndexOf(".asp")==url.length()-4){
				type="html";			
			}else if(url.lastIndexOf(".xls")==url.length()-4){
				type="xls";
			}else if(url.lastIndexOf(".rdf")==url.length()-4){
				type="rdf";
			}else if(url.lastIndexOf(".rdfs")==url.length()-5){
				type="rdfs";
			}else if(url.lastIndexOf(".bib")==url.length()-4){
				type="bib";
			}else if(url.lastIndexOf(".csp")==url.length()-4){
				type="csp";
			}else if(url.lastIndexOf(".xml")==url.length()-4){
				type="xml";
			}else if(url.lastIndexOf(".js")==url.length()-3){
				type="js";
			}else if(url.lastIndexOf(".html")==url.length()-5){
				type="html";
			}else if(url.lastIndexOf(".owl")==url.length()-4){
				type="owl";
			}else if(url.lastIndexOf(".dtd")==url.length()-4){
				type="dtd";
			}else if(url.lastIndexOf(".ps")==url.length()-3){
				type="ps";
			}else if(url.lastIndexOf(".txt")==url.length()-4){
				type="txt";
			}else if(url.lastIndexOf(".svg")==url.length()-4){
				type="svg";
			}else if(url.lastIndexOf(".rq")==url.length()-3){
				type="rq";
			}else if(url.lastIndexOf(".n3")==url.length()-3){
				type="n3";
			}else if(url.lastIndexOf(".txml")==url.length()-5){
				type="txml";
			}else if(url.lastIndexOf(".pls")==url.length()-4){
				type="pls";
			}else if(url.lastIndexOf(".php")==url.length()-4){
				type="php";
			}else if(url.lastIndexOf(".xsd")==url.length()-4){
				type="xsd";
			}else if(url.lastIndexOf(".xul")==url.length()-4){
				type="xul";
			}else if(url.lastIndexOf(".htm")==url.length()-4){
				type="htm";
			}else if(url.lastIndexOf(".arff")==url.length()-5){
				type="arff";
			}else if(url.lastIndexOf(".mht")==url.length()-4){
				type="mht";
			}else if(url.lastIndexOf(".aspx")==url.length()-5){
				type="html";
			}else if(url.lastIndexOf(".cfm")==url.length()-4){
				type="cfm";	
			}else if(url.lastIndexOf(".atom")==url.length()-5){
				type="atom";	
			}else if(url.lastIndexOf(".zip")==url.length()-4){
				type="zip";	
			}else if(url.lastIndexOf(".mp3")==url.length()-4){
				type="mp3";
			}else if(url.lastIndexOf(".mp4")==url.length()-4){
				type="mp4";			
			}else if(url.lastIndexOf(".csv")==url.length()-4){
				type="csv";		
			}else if(url.lastIndexOf(".mov")==url.length()-4){
				type="mov";			
			}else if(url.lastIndexOf(".m4v")==url.length()-4){
				type="m4v";				
			}else if(url.lastIndexOf(".shtml")==url.length()-6){
				type="shtml";				
			}else if(url.lastIndexOf(".rm")==url.length()-3){
				type="rm";		
			}else if(url.lastIndexOf(".ram")==url.length()-4){
				type="ram";		
			}else if(url.lastIndexOf(".swf")==url.length()-4){
				type="swf";					
			}else if(url.lastIndexOf(".wmv")==url.length()-4){
				type="wmv";		
			}else if(url.lastIndexOf(".rmvb")==url.length()-5){
				type="rmvb";				
			}else if(url.lastIndexOf(".cat")==url.length()-4){
				type="cat";			
			}else if(url.lastIndexOf(".decl")==url.length()-5){
				type="decl";					
			}else if(url.lastIndexOf(".ent")==url.length()-4){
				type="ent";					
			}else if(url.lastIndexOf(".tif")==url.length()-4){
				type="tif";					
			}else if(url.lastIndexOf(".rtf")==url.length()-4){
				type="rtf";					
			}else if(url.lastIndexOf(".shtm")==url.length()-5){
				type="shtml";				
			}else if(url.lastIndexOf(".rss")==url.length()-4){
				type="rss";		
			}else if(url.lastIndexOf(".pps")==url.length()-4){
				type="pps";		
			}else if(url.lastIndexOf(".src")==url.length()-4){
				type="src";				
			}else if(url.lastIndexOf(".dtl")==url.length()-4){
				type="dtl";	
			}else if(url.lastIndexOf(".jsz")==url.length()-4){
				type="jsz";	
			}else if(url.lastIndexOf(".cssz")==url.length()-5){
				type="cssz";	
			}else if(url.lastIndexOf(".jpe")==url.length()-4){
				type="jpe";
			}else if(url.lastIndexOf(".mpg")==url.length()-4){
				type="mpg";				
			}else if(url.lastIndexOf(".dot")==url.length()-4){
				type="dot";				
			}else if(url.lastIndexOf(".wma")==url.length()-4){
				type="wma";
			}else if(url.lastIndexOf(".rv")==url.length()-3){
				type="rv";
			}else if(url.lastIndexOf(".pptx")==url.length()-5){
				type="pptx";			
			}else if(url.lastIndexOf(".xlsx")==url.length()-5){
				type="xlsx";
			}else if(url.lastIndexOf(".pdf")==url.length()-4){
				type="pdf";
			}
			else {
				type = "html";
			}
		}
		return type;
	}

		

}
