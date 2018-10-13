package com.chenxing.common.util;

import java.util.List;

/**
 * //分页封装函数
 * 
 * @param <T>
 */
public class PageUtil {
	/**
	 * 排序规则
	 */
	private String orderby;
	
	/**
	 * 分页数据
	 */
	private List<?> records;

	/**
	 * 总页数 这个数是计算出来的
	 * 
	 */
	private Long pageCount;

	/**
	 * 每页显示几条记录
	 */
	private Long pageSize;

	/**
	 * 默认 当前页 为第一页 这个数是计算出来的
	 */
	private Long pageNow;

	/**
	 * 总记录数
	 */
	private Long rowCount;

	/**
	 * 从第几条记录开始
	 */
	private Long startPage;

	/**
	 * 规定显示5个页码
	 */
	private Long pagecode;

	public PageUtil() {
	}

	/**
	 * 要获得记录的开始索引　即　开始页码
	 * 
	 * @return
	 */
	public Long getFirstResult() {
		return (this.pageNow - 1) * this.pageSize;
	}

	public Long getPagecode() {
		return pagecode;
	}

	public void setPagecode(Long pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * 使用构造函数，，强制必需输入 每页显示数量　和　当前页
	 * 
	 * @param pageSize
	 *            　　每页显示数量
	 * @param pageNow
	 *            　当前页
	 */
	public PageUtil(Long pageSize, Long pageNow, Long _rowCount) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
		this.rowCount = _rowCount;
	}

	/**
	 * 使用构造函数，，强制必需输入 当前页
	 * 
	 * @param pageNow
	 *            　当前页
	 */
	public PageUtil(Long pageNow) {
		this.pageNow = pageNow;
		startPage = (this.pageNow - 1) * this.pageSize;
	}

	/**
	 * 查询结果方法 把　记录数　结果集合　放入到　PageView对象
	 * 
	 * @param rowCount
	 *            总记录数
	 * @param records
	 *            结果集合
	 */

	public void setQueryResult(Long rowCount, List<?> records) {
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
		setPageCount(this.rowCount % this.pageSize == 0 ? this.rowCount / this.pageSize : this.rowCount / this.pageSize + 1);
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public Long getPageNow() {
		return pageNow;
	}

	public void setPageNow(Long pageNow) {
		this.pageNow = pageNow;
	}

	public Long getPageCount() {

		pageCount = this.rowCount % this.pageSize == 0 ? (this.rowCount / this.pageSize)
				: (this.rowCount / this.pageSize + 1);

		return pageCount;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getRowCount() {
		return rowCount;
	}

	public Long getStartPage() {
		return startPage;
	}

	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	@Override
	public String toString() {
		return "PageView [ pageCount=" + pageCount + ", pageSize=" + pageSize + ", pageNow=" + pageNow 
			+ ", rowCount=" + rowCount + ", startPage=" + startPage + ", pagecode=" + pagecode + ",orderby=" + orderby + "]";
	}

}
