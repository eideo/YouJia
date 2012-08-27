package com.arc.interceptor;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.arc.entity.Admin;
import com.arc.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@Component
@SuppressWarnings("rawtypes")
public class PermissionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;
	@Resource
	AdminService adminService;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext request = ActionContext.getContext();
		Map session = request.getSession();

		String basePath = ServletActionContext.getRequest().getContextPath();
		String uri = ServletActionContext.getRequest().getRequestURI()
				.replaceFirst(basePath, "");

		if (uri.indexOf("/admin/") > -1) {
			Admin operator = (Admin) session.get("operator");

			if (operator != null) {
				operator = adminService.find(operator.getId());
				session.put("operator", operator);
			}
			if (operator == null) {
				return "login";
			}

			String functions = adminService.getFunctions(operator);
			session.put("functions", functions);

			if (functions == null || !functions.contains(uri)) {
				request.put("message", "您的操作权限不足，请联系管理员");
				return "error";
			}
		}
		return invocation.invoke();
	}

	public void init() {
	}

	public void destroy() {
	}
}
