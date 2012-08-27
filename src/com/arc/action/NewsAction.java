package com.arc.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.News;
import com.arc.service.NewsService;
import com.google.common.base.Strings;

@Controller
@Scope("prototype")
public class NewsAction extends BaseAction {
	private News news;
	private List<News> allNews;
	@Resource
	NewsService newsService;

	public NewsAction() {
		this.news = new News();
	}

	public String list() {
		list = newsService.findByPager(pager);
		if (list.isEmpty())
			result = Result.FAIL;
		return LIST;
	}

	public String edit() {
		news = newsService.findById(news.getId());
		return EDIT;
	}

	public String add() {
		return ADD;
	}

	public String update() {
		newsService.update(news);
		return list();
	}

	public String delete() {
		newsService.delete(news.getId());
		return list();
	}

	public String save() {
		news.setUsername(operator.getName());
		newsService.save(news);
		return list();
	}

	public String listNews() {
		String pageNo = ServletActionContext.getRequest().getParameter("pn");
		pager.setPageSize("12");
		if (Strings.isNullOrEmpty(pageNo)) {
			pager.setCurrentPage("1");
		} else {
			pager.setCurrentPage(pageNo);
		}
		allNews = newsService.findByPager(pager);
		return "list_news";
	}

	public String query() {
		pager.setCurrentPage("1");
		return list();
	}

	public String view() {
		news = newsService.findById(news.getId());
		return "view";
	}

	public News getNews() {
		return news;
	}

	public List<News> getAllNews() {
		return allNews;
	}

}
