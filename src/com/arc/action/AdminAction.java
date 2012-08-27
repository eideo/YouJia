package com.arc.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Admin;
import com.arc.entity.Role;
import com.opensymphony.xwork2.ActionContext;

/**
 * 系统用户管理
 */

@Controller
@Scope("prototype")
public class AdminAction extends BaseAction {

	private String checkCode = "";
	private Admin admin;

	public AdminAction() {
		admin = new Admin();
	}

	public String save() {
		admin.setCreateTime(new Date());
		adminService.save(admin);
		return list();
	}

	public String update() {
		Admin temp = adminService.find(admin.getId());
		temp.setUsername(admin.getUsername());
		temp.setName(admin.getName());
		if (admin.getPassword().length() > 0)
			temp.setPassword(admin.getPassword());
		temp.setSex(admin.getSex());
		temp.setEmail(admin.getEmail());
		temp.setPhone(admin.getPhone());
		temp.setValid(admin.getValid());
		temp.setRoles(admin.getRoles());
		adminService.update(temp);
		return list();
	}

	public String delete() {
		adminService.deleteById(admin.getId());
		return list();
	}

	public String edit() {
		admin = adminService.find(admin.getId());
		return EDIT;
	}

	public String list() {
		list = adminService.findByPager(pager);
		return LIST;
	}

	public String login() {
		if (admin != null && admin.getUsername() != null
				&& admin.getUsername().length() > 0) {
			if (!checkCode.equals(ActionContext.getContext().getSession()
					.get("randomCode"))) {
				message = "验证码错误，请重新输入";
			} else {
				admin = adminService.check(admin.getUsername(),
						admin.getPassword());
				if (admin != null) {
					admin.setLastIp(ServletActionContext.getRequest()
							.getRemoteAddr());
					admin.setLastTime(new Date());
					adminService.updateForLogin(admin);

					ActionContext.getContext().getSession()
							.put("operator", admin);
					ActionContext.getContext().getSession()
							.put("functions", adminService.getFunctions(admin));
					return SUCCESS;
				} else {
					message = "用户名或密码错误，请重新输入";
				}
			}
		}
		return LOGIN;
	}

	public String logout() {
		ActionContext.getContext().getSession().put("operator", null);
		return LOGIN;
	}

	public String info() {
		admin = adminService.find(operator.getId());
		return "info";
	}

	public String updateInfo() {
		Admin temp = adminService.find(operator.getId());
		temp.setName(admin.getName());
		if (admin.getPassword().length() > 0) {
			temp.setPassword(admin.getPassword());
		}
		temp.setSex(admin.getSex());
		temp.setEmail(admin.getEmail());
		temp.setPhone(admin.getPhone());
		adminService.update(temp);
		ActionContext.getContext().getSession().put("operator", temp);
		result = Result.SUCCESS;
		return "info";
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@SuppressWarnings("unchecked")
	public void setRoles(String roles) {
		if (roles != null) {
			roles = roles.replace("，", ",").replace(" ", "");
			String[] ids = roles.split(",");

			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					Role role = new Role();
					role.setId(new Integer(ids[i]));
					admin.getRoles().add(role);
				}
			}
		}
	}

	public List<Role> getRoleList() {
		return roleService.findAll();
	}
}
