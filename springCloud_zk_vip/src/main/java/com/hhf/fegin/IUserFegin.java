package com.hhf.fegin;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhf.api.fallback.FallBackCommon;

/**
 * user(zk-user)的fegin客户端
 * 统一处理服务降级FallBackCommon类。
 * @author hhf
 *
 */

@FeignClient(name="zk-user",fallback=FallBackCommon.class)
public interface IUserFegin {

	//调用user的接口
	@RequestMapping("/user/getTimeOut")
	public Map<String,Object> getTimeOut(@RequestParam("times") Long times);
}
