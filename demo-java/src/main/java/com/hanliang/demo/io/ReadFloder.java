package com.hanliang.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFloder {
	
	public static void main(String[] args){
		
		String floder = "E:\\shihua";
		File file = new File(floder);
		String[] fileList = file.list();
		for(String fileName : fileList){
			File file2 = new File(floder+"\\"+fileName);
			String[] fileList2 = file2.list();
			for(String name : fileList2){
				readFile(floder+"\\"+fileName+"\\"+name);
			}
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readFile(String fileName){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = br.readLine()) != null){
				String[] a = line.split(",");
				String serial = a[2];
				String mac = a[4];
				writeFile(serial+","+mac);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static void writeFile(String data){
		try {
			if(bw == null){
				bw = new BufferedWriter(new FileWriter("E:\\shihua\\serial-mac.txt"));
			}
			bw.write(data);
			bw.newLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	private static BufferedWriter bw;
}
