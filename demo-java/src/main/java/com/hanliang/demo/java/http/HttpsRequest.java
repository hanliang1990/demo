package com.hanliang.demo.java.http;

import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;
import java.net.MalformedURLException;  
import java.net.URL;  
import java.security.GeneralSecurityException;  
import java.security.KeyStore;  
  




import javax.net.ssl.HostnameVerifier;  
import javax.net.ssl.HttpsURLConnection;  
import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManagerFactory;  

public class HttpsRequest {
	public static KeyStore getKeyStore(String password, String keyStorePath)  
            throws Exception {  
        // 实例化密钥库  
        KeyStore ks = KeyStore.getInstance("JKS");  
        // 获得密钥库文件流  
        FileInputStream is = new FileInputStream(keyStorePath);  
        // 加载密钥库  
        ks.load(is, password.toCharArray());  
        // 关闭密钥库文件流  
        is.close();  
        return ks;  
    }
	
	public static SSLContext getSSLContext(String password,  
            String keyStorePath, String trustStorePath) throws Exception {  
        // 实例化密钥库  
        KeyManagerFactory keyManagerFactory = KeyManagerFactory  
                .getInstance(KeyManagerFactory.getDefaultAlgorithm());  
        // 获得密钥库  
        KeyStore keyStore = getKeyStore(password, keyStorePath);  
        // 初始化密钥工厂  
        keyManagerFactory.init(keyStore, password.toCharArray());  
  
        // 实例化信任库  
        TrustManagerFactory trustManagerFactory = TrustManagerFactory  
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        // 获得信任库  
        KeyStore trustStore = getKeyStore(password, trustStorePath);  
        // 初始化信任库  
        trustManagerFactory.init(trustStore);  
        // 实例化SSL上下文  
        SSLContext ctx = SSLContext.getInstance("TLS");  
        // 初始化SSL上下文  
        ctx.init(keyManagerFactory.getKeyManagers(),  
                trustManagerFactory.getTrustManagers(), null);  
        // 获得SSLSocketFactory  
        return ctx;  
    }  
	
	public static void initHttpsURLConnection(String password,  
            String keyStorePath, String trustStorePath) throws Exception {  
        // 声明SSL上下文  
        SSLContext sslContext = null;  
        // 实例化主机名验证接口  
        HostnameVerifier hnv = new MyHostnameVerifier();  
        try {  
            sslContext = getSSLContext(password, keyStorePath, trustStorePath);  
        } catch (GeneralSecurityException e) {  
            e.printStackTrace();  
        }  
        if (sslContext != null) {  
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext  
                    .getSocketFactory());  
        }  
        HttpsURLConnection.setDefaultHostnameVerifier(hnv);  
    }  
	
	 public static String post(String httpsUrl, String param) {  
	        HttpsURLConnection urlCon = null;  
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {  
	            urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();  
	            urlCon.setDoInput(true);  
	            urlCon.setDoOutput(true);  
	            urlCon.setRequestMethod("POST"); 
	            urlCon.setRequestProperty("Accept-Charset", "UTF-8");
	            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf8");
	            out = new PrintWriter(urlCon.getOutputStream());
	            out.print(param);
	            out.flush();
	            //设置为gbk可以解决服务器接收时读取的数据中文乱码问题  
	            urlCon.getOutputStream().flush();  
	            urlCon.getOutputStream().close();  
	            in = new BufferedReader(
	                    new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }  
	  
	    /** 
	     * 测试方法. 
	     * @param args 
	     * @throws Exception 
	     */  
	    public static void main(String[] args) throws Exception {  
	        // 密码  
	        String password = "123456";  
	        // 密钥库  
	        String keyStorePath = "E://tomcat.keystore";  
	        // 信任库  
	        String trustStorePath = "E://tomcat.keystore";  
	        // 本地起的https服务  
	        String httpsUrl = "https://localhost:8080/APmanager-webapp/api/login";  
	        String params = "appId=1&accessToken=1&userName=1&password=1";
	        // 传输文本  
	        HttpsRequest.initHttpsURLConnection(password, keyStorePath, trustStorePath);  
	        // 发起请求  
	        System.out.println(HttpsRequest.post(httpsUrl, params));
	    }  
}
