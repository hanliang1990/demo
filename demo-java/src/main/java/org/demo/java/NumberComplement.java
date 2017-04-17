package org.demo.java;

public class NumberComplement {
	
	public int findComplement(int num) {
		String a = Integer.toBinaryString(num);
		int len = a.length();
		int b = (1<<len)-1;
		return num^b;
    }

	public static void main(String[] args){
		NumberComplement nc = new NumberComplement();
		System.out.println(nc.findComplement(90));
	}
}
