package com.hhf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient//注册中心是zk或Consul时，向注册中心注册信息
@EnableFeignClients//开启fegin
@EnableHystrix//开启Hystrix
public class VipApp {

	public static void main(String[] args) {
		SpringApplication.run(VipApp.class, args);
	}
	
	
	
	//需要往springBoot容器中注册restTemplate的bean。
	@Bean//注入bean
	@LoadBalanced//能让这个restTemplate实例在请求时拥有客户端负载均衡的能力。
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
