package org.las.tools.FingerPrinter;

public class Hamming {

	public static int distance(int[] code1, int[] code2){
		if(code1.length != code2.length){
			return -1;
		}
		int distance = 0;
		for(int i=0;i<code1.length;i++){
			if(code1[i]!=code2[i]){
				distance ++;
			}
		}
		return distance;
	}
	
	public static int distance(String str1, String str2){
		if(str1.length() != str2.length()){
			return -1;
		}
		int distance = 0;
		for(int i=0;i<str1.length();i++){
			if(str1.charAt(i)!=str2.charAt(i)){
				distance++;
			}
		}
		return distance;
	}
	
	public static int distance(long l1, long l2){
		int distance = 0;
		long l = l1^l2;
		while(l>0){
			if((l&1) == 1){
				distance++;
			}
			l >>= 1;
		}
		return distance;
	}
	
}
