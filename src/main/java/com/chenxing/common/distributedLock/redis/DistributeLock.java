package com.chenxing.common.distributedLock.redis;

/**
 * 全局锁，包括锁的名称
 */
public class DistributeLock {
	private String name;
	private String value;

	public DistributeLock(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}