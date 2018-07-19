package com.chenxing.common.result;

import java.util.List;

/**
 * 
 * @author liuxing
 *
 * @param <T>
 */
public class BasePageResult<T> extends BaseResultSupport {
	private static final long serialVersionUID = 7378807577314788084L;
	protected int totalCount;
	protected List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 失败
	 * 
	 * @param abstractReturnCode
	 * @return
	 */
	public static BasePageResult fail(final AbstractReturnCode abstractReturnCode) {
		BasePageResult baseResult = new BasePageResult();
		baseResult.setReturnCode(abstractReturnCode);
		return baseResult;
	}

}
