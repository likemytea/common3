package com.chenxing.common.distributedKey;

/**  
* Description:
* @author liuxing
* @date 2018年7月27日  
* @version 1.0  
*/
public class ServerIdentityGenerater {
	// TODO;
	/**
	 * 获取容器的instanceid http://39.107.82.1:8761/eureka/apps，这个id只有在
	 * 创建容器时产生，且是容器的惟一标识， 我们就拿这个作为分布式主键的唯一标识，将它保留在数据库内， 服务的serverIdentity从数据库获取。
	 * 
	 * 
	 * 数据库表结构 物理主键也是身份标识 identity_id 容器id eureka_instance_id
	 * 连续不在线次数（天数），因为是每天统计一次，所以也可以认为是天数。 outlinetime
	 * 
	 * 
	 * 服务启动时，获取eureka_instanceid ,绑定新的身份标识identity_id 到数据库。
	 * 每天将eureka实例在数据库的数据列表上做一次签到，在线设置为0 ，不在线+1，
	 * 定时任务（每个月执行一次）清理数据库的身份标识，连续60次不在线，从数据库中剔除，身份标识回收。(执行此操作时锁全表)
	 * 
	 * 
	 */
}
