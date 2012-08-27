package com.arc.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Admin;
import com.arc.service.AdminService;
import com.arc.service.ModuleService;
import com.arc.service.RoleService;
import com.arc.util.Pager;
import com.opensymphony.xwork2.ActionContext;

/**
 * action基类
 */

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class BaseAction {

	protected Result result;// 返回JSP结果
	protected String message;// 返回JSP详细信息
	protected List list;
	protected Admin operator;
	@Resource
	protected Pager pager;
	@Resource
	protected ModuleService moduleService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected AdminService adminService;
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";
	protected static final String INPUT = "input";
	protected static final String LOGIN = "login";
	protected static final String ADD = "add";
	protected static final String EDIT = "edit";
	protected static final String LIST = "list";

	@PostConstruct
	public void init() {
		operator = (Admin) ActionContext.getContext().getSession()
				.get("operator");
	}

	public String add() {
		return ADD;
	}

	public String edit() {
		return EDIT;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List getList() {
		return list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBasePath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	public void setMid(Integer id) {
		ActionContext.getContext().getSession()
				.put("currentPosition", moduleService.getCurrentPosition(id));
	}

	public String getResult() {
		return result.toString();
	}

	public static enum Result {
		SUCCESS, FAIL
	}
}
