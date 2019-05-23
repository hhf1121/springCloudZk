package com.hhf.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/VIP")
public class VipApiController {

	// 在springCloud中，两种方式调用rest和fegin（springcloud）

	@Autowired
	private RestTemplate restTemplate;// 由springBoot WEB组件提供

	@RequestMapping("getUserStr")
	public String getUserStr() {
		// VIP--调用User,1.通过httpClient写死。2.通过注册zk上的别名(集群负载均衡，使用别名)
		String resukt = restTemplate.getForObject("http://zk-user/user/getUserStr", String.class);// 1
		return "VIP--调用User:" + resukt;
	}

	@RequestMapping("getUserData")
	public String getVIP(Integer type) {
		// VIP--调用User,1.通过httpClient写死。2.通过注册zk上的别名
		  Map result = restTemplate.getForObject("http://zk-user/user/getUserData?yes="+type, Map.class);// 2
		 // 如果想以别名方式调用服务，restTemplate对象需要依赖ribbon负载均衡器
		// 注解@LoadBalanced能让这个restTemplate实例在请求时拥有客户端负载均衡的能力。
		return "VIP--调用User:" + result;
	}

}
