package com.chenxing.common.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.chenxing.common.pagination.IllegalArgumentException;
import com.chenxing.common.pagination.Pagination;
import com.chenxing.common.pagination.PaginationResult;
import com.chenxing.common.pagination.SortBy;
import com.chenxing.common.pagination.SortType;

/**
 * 耶稣基督说：施比受更有福
 * Created by liuxing on 2018/8/17.
 */
public class MyJdbcTemplate extends JdbcTemplate {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public MyJdbcTemplate() {
	}

	public MyJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}

	public MyJdbcTemplate(DataSource dataSource, boolean lazyInit) {
		super(dataSource, lazyInit);
	}

	public <T> PaginationResult<T> queryForPage(String sql, int currentpage, int pagesize, String sortBy,
			SortType sortType, RowMapper<T> rowMapper) throws DataAccessException {
		Pagination pagination = assemblePaging(currentpage, pagesize, sortBy, sortType);
		return queryForPage(sql, pagination, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				return;
			}
		}, rowMapper);
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
		try {
			super.queryForObject(sql, rowMapper, args);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

		List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		return DataAccessUtils.requiredSingleResult(results);
	}
	/**
	 * 组装分页及排序对象
	 * 
	 */
	private Pagination assemblePaging(int _currentpage, int _pagesize, String _sortBy, SortType _sortType) {
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(_currentpage == 0 ? 1 : _currentpage);
		pagination.setPageSize(_pagesize == 0 ? 10 : _pagesize);
		SortBy sortBy = null;
		try {
			sortBy = new SortBy(_sortBy, 1, _sortType);
			pagination.addSortBy(sortBy);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return pagination;
	}
	public <T> PaginationResult<T> queryForPage(String sql, Pagination pagination, RowMapper<T> rowMapper)
			throws DataAccessException {

		return queryForPage(sql, pagination, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				return;
			}
		}, rowMapper);
	}

	public <T> PaginationResult<T> queryForPage(String sql, Pagination pagination, PreparedStatementSetter var2,
			RowMapper<T> var3) throws DataAccessException {

		PaginationResult<T> result = new PaginationResult<T>();

		// 获取记录条数
		String countSql = "select count(1) as count from (" + sql + ") temp";
		List<Integer> countList = super.query(countSql, var2, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Integer(resultSet.getInt("count"));
			}
		});
		result.setTotalCount(countList.get(0));
		result.setCurrentPage(pagination.getCurrentPage());
		result.setPageSize(pagination.getPageSize());

		int pageCount = result.getTotalCount() % result.getPageSize();
		result.setTotalPage(pageCount == 0 ? (result.getTotalCount() / result.getPageSize())
				: (result.getTotalCount() / result.getPageSize() + 1));

		String sortSql = parseSort(pagination);
		if (sortSql != null) {
			sql += sortSql;
		}
		sql += parseLimit(pagination);
		System.out.println(sql);
		List<T> data = super.query(sql, var2, var3);
		result.setData(data);

		return result;
	}

	private String parseLimit(Pagination pagination) {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" ");
		stringBuffer.append("limit");
		stringBuffer.append(" ");
		stringBuffer.append((pagination.getCurrentPage() - 1) * pagination.getPageSize());
		stringBuffer.append(",");
		stringBuffer.append(pagination.getPageSize());

		return stringBuffer.toString();
	}

	private String parseSort(Pagination pagination) {

		List<SortBy> list = pagination.getSorts();
		if (list.size() == 0) {
			return null;
		}

		// Collections.sort(list);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" ");
		stringBuffer.append("order by ");
		for (SortBy sortBy : list) {
			stringBuffer.append(sortBy.getColName());
			stringBuffer.append(" ");
			stringBuffer.append(sortBy.getSortType());
			stringBuffer.append(",");
		}

		return stringBuffer.toString().substring(0, stringBuffer.length() - 1);

	}

}
