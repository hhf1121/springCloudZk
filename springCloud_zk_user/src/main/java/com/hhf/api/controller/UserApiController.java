package com.hhf.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.hhf.entity.User;
import com.hhf.service.UserService;

@RestController
@RequestMapping("/user")
public class UserApiController {
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUserStr")
	public String getUserStr(){
		return port+":getUserStr...";
	}
	
	@RequestMapping(value="getUserData")
	public Map<String,Object> getUserData(Integer yes){
		Map<String,Object> result=new HashMap<String, Object>();
		List<User> queryVIP = userService.queryVIP(yes);
		result.put("data", queryVIP);
		result.put("success", true);
		return result;
	}
	
	@RequestMapping("/getTimeOut")
	public Map<String,Object> getTimeOut(Long times){
		System.out.println(times+":getTimeOut...");
		try {
			Thread.sleep(times);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		HashMap<String, Object> newHashMap = Maps.newHashMap();
		newHashMap.put("info", "调用user的getTimeOut方法");
		newHashMap.put("param", times);
		return newHashMap;
	}
	
	
}
