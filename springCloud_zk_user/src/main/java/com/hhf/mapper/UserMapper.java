package com.hhf.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hhf.entity.User;


//可不加注解、但是启动入门需扫包。
@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE yes=#{yes}")
	List<User> findByType(@Param("yes") Integer yes);
}
