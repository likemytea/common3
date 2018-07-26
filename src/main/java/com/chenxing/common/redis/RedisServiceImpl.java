package com.chenxing.common.redis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void set(String key, Object value) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
	}

	@Override
	public Object get(String key) {
		ValueOperations<String, Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	protected boolean flushdb() {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return true;
			}
		});
	}

	public void setHash() {
		flushdb();
		HashOperations<String, String, String> rhash = redisTemplate.opsForHash();
		rhash.put("book", "科技", "c++程序教程");
		Map<String, String> map = new HashMap<String, String>();
		map.put("小说", "三只松鼠");
		map.put("小说", "七个小矮人和一个公主");
		rhash.putAll("book", map);
		// vo.putIfAbsent("book", "mc", "www");
		rhash.increment("book", "行政", 14);
		rhash.increment("book", "烹饪", 17);
		// vo.delete("book", "cl", "yw");
	}

	public void getHash() {
		HashOperations<String, String, String> rhash = redisTemplate.opsForHash();
		System.out.println(rhash.get("book", "科技"));
		System.out.println(String.valueOf(rhash.get("book", "小说")));
		System.out.println(rhash.multiGet("book", Arrays.asList("科技", "小说", "行政")));
		System.out.println(rhash.hasKey("book", "烹饪"));
		System.out.println(rhash.entries("book"));
		System.out.println(rhash.keys("book"));
		System.out.println(rhash.values("book"));
		System.out.println(rhash.size("book"));

	}

}
