package com.chenxing.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuxing
 */
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 8306644012173689576L;
	private long totalCount;
	private long totalPage;

	private List<T> array;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setArray(final List<T> _array) {
		if (_array == null) {
			this.array = new ArrayList<T>();
		} else {
			this.array = _array;
		}
	}

	public List<T> getArray() {
		return Collections.unmodifiableList(array);
	}
}
