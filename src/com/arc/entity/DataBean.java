package com.arc.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.util.Assert;

/**
 * 数据容器
 * 
 * @author Eideo.Guo
 */
public class DataBean extends HashMap<String, Object> {
	private static final long serialVersionUID = 6008609134578238141L;
	private static Number ZERO = 0;
	public static final DataBean EMPTY = new DataBean();

	public DataBean(Map<String, Object> map) {
		super(map);
	}

	public DataBean(Object... args) {
		put(args);
	}

	public void put(Object... args) {
		Assert.isTrue(args.length % 2 == 0);
		for (int i = 0; i < args.length; i += 2) {
			put((String) args[i], args[i + 1]);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, String key) {
		return (T) get(key);
	}

	public <T> T get(Class<T> clazz, String key, T defaultValue) {
		T value = get(clazz, key);
		return value == null ? defaultValue : value;
	}

	public Number getNumber(String key, Number defaultValue) {
		return get(Number.class, key, defaultValue);
	}

	public Date getDate(String key) {
		return get(java.sql.Date.class, key);
	}

	public int getInt(String key) {
		return getInt(key, ZERO);
	}

	public int getInt(String key, Number defaultValue) {
		return getNumber(key, defaultValue).intValue();
	}

	public Integer getInteger(String key) {
		Object o = get(key);
		return o instanceof Number ? ((Number) o).intValue() : null;
	}

	public Integer getInteger(String key, Integer defaultValue) {
		Integer value = getInteger(key);
		return value == null ? defaultValue : value;
	}

	// public Long getLong(String key) {
	// Object o = get(key);
	// return o instanceof Number ? ((Number) o).longValue() : null;
	// }
	//
	// public Long getLong(String key, Long defaultValue) {
	// Long value = getLong(key);
	// return value == null ? defaultValue : value;
	// }

	public String getString(String key) {
		return get(String.class, key);
	}

	public Boolean getBoolean(String key) {
		return get(Boolean.class, key);
	}

	public Byte getByte(String key) {
		return get(Byte.class, key);
	}

	public Short getShort(String key) {
		return get(Short.class, key);
	}

	public Float getFloat(String key) {
		return get(Float.class, key);
	}

	@SuppressWarnings("unchecked")
	public static DataBean fromMap(Object map) {
		return map == null ? new DataBean() : new DataBean(
				(Map<String, Object>) map);
	}

	public String toJsonString() {
		return JSONObject.fromObject(this).toString();
	}
}
