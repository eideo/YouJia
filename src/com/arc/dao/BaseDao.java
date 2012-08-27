package com.arc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.arc.util.Order;
import com.arc.util.Pager;
import com.arc.util.Property;

public class BaseDao<T extends Serializable> {
	protected JdbcTemplate jdbcTemplate;
	protected TransactionTemplate transactionTemplate;

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

	@Resource
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(Object entity) {
		getSession().merge(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	public T find(Serializable id) {
		T entity = (T) getSession().get(clazz, id);
		return entity;
	}

	public List<T> findAll(Order order, Property... propertys) {
		return findByPager(null, new Order[] { order }, propertys);
	}

	public List<T> findAll(Order[] orders, Property... propertys) {
		return findByPager(null, orders, propertys);
	}

	public List<T> findByPager(Pager pager, Order order, Property... propertys) {
		return findByPager(pager, new Order[] { order }, propertys);
	}

	public List<T> findByPager(Pager pager, Order[] orders,
			Property... propertys) {
		Criteria criteria = getSession().createCriteria(clazz);
		// ��ѯ����
		for (Property property : propertys) {
			if (property != null)
				criteria.add(property.getCriterion());
		}
		// ��ҳ
		if (pager != null) {
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();

			if (count == null)
				return new ArrayList();
			pager.setTotalSize(count);
			criteria.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			criteria.setMaxResults(pager.getPageSize());
			criteria.setProjection(null);
		}
		// ����
		if (orders != null && orders.length > 0) {
			for (Order order : orders) {
				if (order != null)
					criteria.addOrder(order.getHibernateOrder());
			}
		}
		return criteria.list();
	}

	public Integer countByProperty(String propertyName, Property... propertys) {
		Criteria criteria = getSession().createCriteria(clazz);
		// ��ѯ����
		for (Property property : propertys) {
			if (property != null)
				criteria.add(property.getCriterion());
		}
		criteria.setProjection(Projections.count(propertyName));
		return (Integer) criteria.uniqueResult();
	}

	protected List executeQuery(String hql, Object... values) {
		return executeQuery(null, hql, values);
	}

	protected List executeQuery(Pager pager, String hql, Object... values) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		if (pager != null) {
			query.setFirstResult((pager.getCurrentPage() - 1)
					* pager.getPageSize());
			query.setMaxResults(pager.getPageSize());
		}
		return query.list();
	}

	protected Long executeCount(String hql, Object... values) {
		Query query = getSession().createQuery("select count(*) " + hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return (Long) query.list().get(0);

	}

}
