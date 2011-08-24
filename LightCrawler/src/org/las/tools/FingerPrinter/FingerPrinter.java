package org.las.tools.FingerPrinter;
import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKTokenizer;

public class FingerPrinter {

	public static int[] getCode(String str){
		
		IKTokenizer tokenizer = new IKTokenizer(new StringReader(str) , true);
		int[] cal_sim = new int[64];
		try {
			while(tokenizer.incrementToken()){
				TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
				String hash = Long.toBinaryString(HashAlgorithms.mixHash(termAtt.term()));
				int length = hash.length();
				for(int i=0;i<length;i++){
					if(hash.charAt(length-i-1)=='1'){
						cal_sim[i] +=1;
					}else{
						cal_sim[i] -=1;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<cal_sim.length; i++){
			if(cal_sim[i]>0){
				cal_sim[i] = 1;
			}else{
				cal_sim[i] = 0;
			}
		}
		return cal_sim;
	}
	
	public static long getLongCode(String str){
		
		int[] cal_sim = getCode(str);
		long simHash = 0;
		for(int i = 0; i<cal_sim.length; i++){
			if(cal_sim[i]>0){
				simHash = simHash+1<<1;
			}else{
				simHash = simHash<<1 ;
			}
		}
		return simHash;
	}
	
	public static String getHexStringCode(String str){
		
		long simHash = getLongCode(str);
		return Long.toHexString(simHash);
	}
	
	public static String getBinaryStringCode(String str){
		
		int[] cal_sim = getCode(str);
		StringBuffer buf = new StringBuffer();
		for(int i = cal_sim.length-1; i>=0; i--){
			if(cal_sim[i]>0){
				buf.append(1);
			}else{
				buf.append(0);
			}
		}
		return buf.toString();
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String t1 = "我爱北京天安门";
		String t2 = "我爱北京地安门,哈";
		
		System.out.println("code1="+getBinaryStringCode(t1));
		System.out.println("code2="+getBinaryStringCode(t2));
	}

}
