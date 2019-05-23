package com.hhf.service;

/**
 * 开启事务、方法上加@Transactional
 * SpringBoot2.0整合pagehelper
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhf.entity.User;
import com.hhf.mapper.UserMapper;

@Service
public class UserService{

	@Autowired
	private UserMapper userMapper;	
	
	public List<User> queryVIP(Integer yes) {
		return userMapper.findByType(yes);
	}
}
