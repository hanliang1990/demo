package com.hanliang.rpc.service;

import com.hanliang.rpc.common.PpcAnnotation;

// 指定远程接口
@PpcAnnotation(HelloService.class)
public class HelloServiceImpl implements HelloService {

	@Override
	public String hello(String name) {
		return "Hello! " + name;
	}

}
