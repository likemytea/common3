package com.chenxing.common.redis;

public interface RedisService {
	void set(String key, Object value);

	Object get(String key);

	public void setHash();

	public void getHash();

}
