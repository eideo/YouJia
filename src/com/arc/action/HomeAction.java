package com.arc.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.News;
import com.arc.entity.Region;
import com.arc.entity.User;
import com.arc.entity.Voter;
import com.arc.service.NewsService;
import com.arc.service.RegionService;
import com.arc.service.UserService;
import com.google.common.base.Strings;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class HomeAction {
	private List<Region> regions;
	private Voter voter;
	private News news;
	private List<News> lastestNews;
	private List<User> lastestUsers;
	private int regionId;
	private int nowUserId;
	private Date curDate;
	@Resource
	RegionService regionService;
	@Resource
	NewsService newsService;
	@Resource
	UserService userService;

	public String index() {
		checkVoter();
		lastestNews = newsService.findLastestNews();
		regions = regionService.getRegions();
		curDate = new Date();
		return "index";
	}

	private void checkVoter() {
		Object o = ActionContext.getContext().getSession().get("voter");
		if (o != null) {
			voter = (Voter) o;
		}
	}

	public String logout() {
		voter = null;
		ActionContext.getContext().getSession().remove("voter");
		return "index";
	}

	public String login() {
		return "login";
	}

	public String users() {
		String regionId = ServletActionContext.getRequest().getParameter("r");
		lastestUsers = userService.findLastestUsers(regionId);
		return "users";
	}

	public String previous() {
		checkVoter();
		return "previous";
	}

	public String news() {
		String id = ServletActionContext.getRequest().getParameter("id");
		try {
			news = newsService.findById(Integer.parseInt(id));
		} catch (Exception e) {
			news = new News();
		}
		return "news";
	}

	public String intro() {
		return "intro";
	}

	public String reward() {
		return "reward";
	}

	public String rank() {
		checkVoter();
		String rid = ServletActionContext.getRequest().getParameter("r");
		if (Strings.isNullOrEmpty(rid)) {
			regionId = 0;
		} else {
			regionId = Integer.parseInt(rid);
		}
		String uid = ServletActionContext.getRequest().getParameter("u");
		if (!Strings.isNullOrEmpty(uid)) {
			nowUserId = Integer.parseInt(uid);
		}
		return "rank";
	}

	public String getBasePath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	public List getRegions() {
		return regions;
	}

	public Voter getVoter() {
		return voter;
	}

	public News getNews() {
		return news;
	}

	public List<News> getLastestNews() {
		return lastestNews;
	}

	public void setLastestNews(List<News> lastestNews) {
		this.lastestNews = lastestNews;
	}

	public List<User> getLastestUsers() {
		return lastestUsers;
	}

	public void setLastestUsers(List<User> lastestUsers) {
		this.lastestUsers = lastestUsers;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(int nowUserId) {
		this.nowUserId = nowUserId;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

}
