package com.hanliang.demo.leetcode;

import java.util.Arrays;

public class HammingDistance {
	
	 public int hammingDistance(int x, int y) {
		 
		 String bx = Integer.toBinaryString(x);
		 String by = Integer.toBinaryString(y);
		 byte[] bxa = bx.getBytes();
		 byte[] bya = by.getBytes();
		 int xlen = bxa.length;
		 int ylen = bya.length;
		 int xk = 0;
		 int yk = 0;
		 for(int i=0;i<xlen;i++){
			 if(bxa[i] == 49){
				 xk++;
			 }
		 }
		 for(int j=0;j<ylen;j++){
			 if(bya[j] == 49){
				 yk++;
			 }
		 }
		 return xk>yk?xk-yk:yk-xk;
	 }
	 
	 public static void main(String[] args){
		 HammingDistance hd = new HammingDistance();
		 System.out.println(hd.hammingDistance(1, 4));
	 }

}
