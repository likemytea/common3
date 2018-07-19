package com.chenxing.common.result;

import java.io.Serializable;

/**
 * Created by liuxing on 2017/5/2.
 */
public class ExtResult implements Serializable {
	private static final long serialVersionUID = 3008317695376928647L;
	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
