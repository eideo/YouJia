package com.arc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.News;
import com.arc.util.Pager;
import com.google.common.collect.Lists;

@Repository
public class NewsDAO {

	@Resource
	private Dbo dbo;

	public void save(News news) {
		String sql = "insert into news(title,create_time,content,username) values(?,now(),?,?)";
		dbo.update(sql, news.getTitle(), news.getContent(), news.getUsername());
	}

	public void update(News news) {
		String sql = "update news set title=?,content=? where id=?";
		dbo.update(sql, news.getTitle(), news.getContent(), news.getId());
	}

	public void delete(int newsId) {
		String sql = "delete from news where id=?";
		dbo.update(sql, newsId);
	}

	public List<News> findByPager(Pager pager) {
		int totalSize = dbo.queryForInt("select count(*) from news");
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo.queryForList(
				"select * from news order by id desc limit ?,?",
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());
		List<News> news = Lists.newArrayList();

		if (tmpList == null)
			return news;
		for (DataBean bean : tmpList) {
			news.add(mapper(bean));
		}
		return news;

	}

	private News mapper(DataBean bean) {
		News news = new News();
		if (bean.containsKey("id"))
			news.setId(bean.getInt("id"));
		if (bean.containsKey("title"))
			news.setTitle(bean.getString("title"));
		if (bean.containsKey("content"))
			news.setContent(bean.getString("content"));
		if (bean.containsKey("create_time")) {
			news.setCreateTime(bean.getDate("create_time"));
		}
		if (bean.containsKey("username"))
			news.setUsername(bean.getString("username"));
		return news;
	}

	public News findById(int id) {
		String sql = "select * from news where id=?";
		DataBean bean = this.dbo.queryForMapBean(sql, id);
		if (bean == null)
			return null;
		else
			return mapper(bean);
	}

	public List<News> findLastestNews() {
		List<DataBean> tmpList = dbo
				.queryForList("select * from news order by id desc limit 0,5");
		List<News> news = Lists.newArrayList();

		if (tmpList == null)
			return news;
		for (DataBean bean : tmpList) {
			news.add(mapper(bean));
		}
		return news;
	}
}
