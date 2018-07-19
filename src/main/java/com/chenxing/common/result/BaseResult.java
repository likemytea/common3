package com.chenxing.common.result;

/**
 * @param <T>
 * @author liuxing
 */
public class BaseResult<T> extends BaseResultSupport {
	private static final long serialVersionUID = 4999091548448313101L;
	protected T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 失败
	 * 
	 * @param abstractReturnCode
	 * @return
	 */
	public static BaseResult fail(final AbstractReturnCode abstractReturnCode) {
		BaseResult baseResult = new BaseResult();
		baseResult.setReturnCode(abstractReturnCode);
		return baseResult;
	}

	/**
	 * 成功
	 * 
	 * @param data
	 * @return
	 */
	public static BaseResult success(final Object data) {
		BaseResult baseResult = new BaseResult();
		baseResult.setData(data);
		return baseResult;
	}
}
