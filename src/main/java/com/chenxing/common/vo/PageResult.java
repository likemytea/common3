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
	private int totalCount;
	private int totalPage;

	private List<T> array;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalPage() {
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
