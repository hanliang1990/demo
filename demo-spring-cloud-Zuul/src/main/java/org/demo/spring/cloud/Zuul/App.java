package org.demo.spring.cloud.Zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@EnableZuulProxy
@SpringCloudApplication
@EnableDiscoveryClient
public class App{
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
