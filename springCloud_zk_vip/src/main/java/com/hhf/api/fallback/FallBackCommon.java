package com.hhf.api.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.hhf.fegin.IUserFegin;

@Component
public class FallBackCommon implements IUserFegin{

	@Override
	public Map<String, Object> getTimeOut(Long times) {
		HashMap<String, Object> newHashMap = Maps.newHashMap();
		newHashMap.put("param",times);
		newHashMap.put("code", 500);
		newHashMap.put("info", "类继承的方法，实现服务降级:fallback");
		return newHashMap;
	}

}
