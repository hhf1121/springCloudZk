vip子系统注册到zk，调用user子系统

1.使用fegin客户端、
2.使用hystrix服务保护（以下两种方式）
	2.1-@HystrixCommand
	2.2-@FeignClient(name="zk-user",fallback=FallBackCommon.class)