package org.demo.java;

import java.util.Arrays;

public class QuickSort {
	
	public static void quickSort(int[] A,int start,int end){
		if(start<end){
			int n = partion(A,start,end);
			quickSort(A,start,n-1);
			quickSort(A,n+1,end);
		}
	}
	
	public static int partion(int[] A,int start,int end){
		int i = start-1;
		int val = A[end];
		for(int j = start; j < end; j++){
			if(A[j] < val){
				int temp = A[j];
				A[j] = A[++i];
				A[i] = temp;
			}
		}
		A[end] = A[++i];
		A[i] = val;
		return i;
	}
	
	public static void main(String[] args){
		int[] a = {5,7,9,0,2,3,6,1,8,4};
		quickSort(a,0,9);
		System.out.println(Arrays.toString(a));
	}
}
