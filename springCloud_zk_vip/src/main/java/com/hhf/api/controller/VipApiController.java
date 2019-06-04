package com.hhf.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;
import com.hhf.fegin.IUserFegin;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/VIP")
public class VipApiController {

	@Autowired
	private DiscoveryClient discoveryClient;//DiscoveryClient：可获取注册中心的信息

	@Autowired
	private IUserFegin userFegin;
	
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
	
	
	@RequestMapping("getZkData")
	public Map<String,Object> getEurekaData(String name){
		Map<String,Object> map=Maps.newHashMap();
		List<ServiceInstance> instances = discoveryClient.getInstances(name);
		map.put("data", instances);
		map.put("success", true);
		return map;
	}
	
	
	/**
	 * fegin客户端调用
	 * 开启hystrix保护
	 * 1.注解的方式
	 * @param times
	 * @return
	 */
	@RequestMapping("/getTimeOut")
//	@HystrixCommand(fallbackMethod="getUserByTimeHystrixBack")
	public Map<String,Object> getUserTime(Long times){
		return userFegin.getTimeOut(times);
	}
	
	/**
	 * 熔断之后，服务降级对应的方法，必须和原方法同参数，同返回。
	 * @param times
	 * @return
	 */
	public Map<String,Object> getUserByTimeHystrixBack(Long times){
		HashMap<String, Object> newHashMap = Maps.newHashMap();
		newHashMap.put("info","请求参数:"+times+"---------服务忙，请稍后再试。。。");
		newHashMap.put("code", 500);
		return newHashMap;
	}
	
	
	/**
	 * fegin客户端调用
	 * 开启hystrix保护
	 * 2.类继承的方式fallback
	 * @param times
	 * @return
	 */
	@RequestMapping("/getTimeOut_fallback")
	public Map<String,Object> getTimeOut_fallback(Long times){
		return userFegin.getTimeOut(times);
	}

}
