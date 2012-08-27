package com.arc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.NewsDAO;
import com.arc.entity.News;
import com.arc.util.Pager;

@Service
public class NewsService {
	@Resource
	NewsDAO newsDAO;

	public void save(News news) {
		newsDAO.save(news);
	}

	public void update(News news) {
		newsDAO.update(news);
	}

	public void delete(int id) {
		newsDAO.delete(id);
	}

	public List<News> findByPager(Pager pager) {
		return newsDAO.findByPager(pager);
	}

	public News findById(int id) {
		return newsDAO.findById(id);
	}

	public List<News> findLastestNews() {
		return newsDAO.findLastestNews();
	}
}
