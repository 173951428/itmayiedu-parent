# itmayiedu-parent

项目模块
itmayiedu-parent               ---父项目

	----- itmayiedu-api-member-service-impl ->会员实现模块
	
	-----itmayiedu-api-order-service-impl  -> 订单实现模块
	
	-----itmayiedu-api-service ->api接口模块
	
		-------itmayiedu-api-member-service	->会员接口模块
		
		-------itmayiedu-api-order-service	->订单接口模块
		
	-----itmayiedu-common	->公共类
	
	-----itmayiedu-eureka-a	->Eureka注册中心
	
	-----itmayiedu-msg-consumer	->RabbitMQ消息消费模块
	
	-----itmayiedu-tx-manager	->分布式配置中心LCN后台管理模块
	
	-----itmayiedu-xxl-jobs		->XXL定时任务模块
	
	-----itmayiedu-zuul-gateway	->Zuul网关模块

springcloud分布式项目整合,集成了mybatisPlus,Redis,zuul网关,ribbon负载均衡,eureka注册中心,fegin服务调用,hystrix服务熔断降级保护,RabbbitMQ,xxL分布式定时任务,LCN分布式事务解决方案,swagger2,springsession分布式session解决方案
