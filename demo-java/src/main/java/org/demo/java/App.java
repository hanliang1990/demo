package org.demo.java;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;

/**
 * Hello world!
 *
 */
public class App {
	static int i;
	
    public static void main( String[] args ) throws Exception{
    	Calendar cal = Calendar.getInstance();
    	cal.set(2017, 2, 3, 11, 0,0);
    	System.out.println(cal.getTime().getTime());
    	cal.set(2017, 2, 3, 18, 0,0);
    	System.out.println(cal.getTime().getTime());
    	
//        System.out.println( URLEncoder.encode("7", "unicode"));
    }
}
