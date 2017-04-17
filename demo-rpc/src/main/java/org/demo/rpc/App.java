package org.demo.rpc;

import com.hanliang.rpc.client.RpcProxy;
import com.hanliang.rpc.service.HelloService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	RpcProxy rpcProxy = new RpcProxy("127.0.0.1",9001);
        try {
			HelloService helloService = rpcProxy.create(HelloService.class);
			String result = helloService.hello("World");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
