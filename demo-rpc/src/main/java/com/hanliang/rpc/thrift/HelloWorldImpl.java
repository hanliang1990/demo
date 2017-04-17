package com.hanliang.rpc.thrift;

public class HelloWorldImpl implements HelloWorldService.Iface {

    public HelloWorldImpl() {
    }

    @Override
    public String sayHello(String username) {
        return "Hi," + username + " ,Welcome to the thrift's world !";
    }

}
