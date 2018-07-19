
package com.chenxing.common.result;

import java.io.Serializable;

/**
 * @author liuxing
 */
public class BaseResultSupport implements Serializable {
	private static final long serialVersionUID = -2235152751651905167L;
	private String resultMsg;
	private int errorCode;

	private boolean success = true;

	private long systemTime = System.currentTimeMillis();

	private ExtResult extResult;

	public ExtResult getExtResult() {
		return extResult;
	}

	public void setExtResult(ExtResult extResult) {
		this.extResult = extResult;
	}

	public BaseResultSupport() {

	}

	public boolean isSuccess() {
		return success;
	}

	public BaseResultSupport setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setReturnCode(AbstractReturnCode abstractReturnCode) {
		if (abstractReturnCode != null) {
			this.resultMsg = abstractReturnCode.getDesc();
			this.errorCode = abstractReturnCode.getCode();
			this.setSuccess(false);// 误删
		}
	}

	public int getErrorCode() {
		return errorCode;
	}

	public long getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(long systemTime) {
		this.systemTime = systemTime;
	}
}
