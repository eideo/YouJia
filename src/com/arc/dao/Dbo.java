package com.arc.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.arc.entity.DataBean;

/**
 * 数据库操作对象
 * 
 * @author Eideo.Guo
 */
public class Dbo {
	private JdbcTemplate jdbcTemplate;
	private TransactionTemplate transactionTemplate;

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		transactionTemplate = new TransactionTemplate(
				new DataSourceTransactionManager(dataSource));
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @return 主键
	 */
	public int insert(final String sql, final Object... args) {
		return transactionTemplate.execute(new TransactionCallback<Number>() {
			@Override
			public Number doInTransaction(TransactionStatus status) {
				if (update(sql, args) == 0) {
					return null;
				}
				return jdbcTemplate.queryForObject("select last_insert_id()",
						Number.class);
			}
		}).intValue();
	}

	/**
	 * @return 影响行数
	 */
	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	public DataBean queryForMapBean(String sql, Object... args) {
		try {
			return (DataBean) jdbcTemplate.queryForObject(sql,
					columnMapBeanRowMapper, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public <T> T queryForObject(String sql, Class<T> requiredType,
			Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, requiredType, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DataBean> queryForList(String sql, Object... args) {
		return (List) jdbcTemplate.query(sql, columnMapBeanRowMapper, args);
	}

	public Integer queryForInt(String sql, Object... args) {
		try {
			return jdbcTemplate.queryForInt(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> queryForList(String sql, Class<T> requiredType,
			Object... args) {
		return (List) jdbcTemplate.queryForList(sql, requiredType, args);
	}

	RowMapper<Map<String, Object>> columnMapBeanRowMapper = new ColumnMapRowMapper() {
		@Override
		protected Map<String, Object> createColumnMap(int columnCount) {
			return new DataBean();
		}
	};
}
