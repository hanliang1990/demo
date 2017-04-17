package com.hanliang.rpc.client;

import java.lang.reflect.Method;
import java.util.UUID;

import com.hanliang.rpc.common.RpcRequest;
import com.hanliang.rpc.common.RpcResponse;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class RpcProxy {
    private String ipAddress;
    private int port;

    public RpcProxy(String ipAddress,int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest request = new RpcRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);

                        RpcClient client = new RpcClient(ipAddress, port); // 初始化 RPC 客户端
                        RpcResponse response = client.send(request); // 通过 RPC客户端发送RPC请求并获取RPC响应
                        System.out.println(response.getResult());
                        if (response.isError()) {
                            throw response.getError();
                        } else {
                            return response.getResult();
                        }
                    }
                });
    }
}
