package com.arc.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Module;
import com.arc.entity.Role;
import com.arc.service.ModuleService;
import com.arc.service.RoleService;

/**
 * 角色权限管理
 */

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction {

	private Role role;
	@Resource
	private RoleService roleService;
	@Resource
	private ModuleService moduleService;

	public RoleAction() {
		role = new Role();
	}

	public String save() {
		roleService.save(role);
		return list();
	}

	public String update() {
		roleService.update(role);
		return list();
	}

	public String delete() {
		roleService.deleteById(role.getId());
		return list();
	}

	public String add() {
		list = moduleService.findAll();
		return ADD;
	}

	public String edit() {
		role = roleService.find(role.getId());
		list = moduleService.findAll();
		return EDIT;
	}

	public String list() {
		list = roleService.findByPager(pager);
		return LIST;
	}

	public Role getRole() {
		return role;
	}

	@SuppressWarnings("unchecked")
	public void setModules(String modules) {
		if (modules != null) {
			modules = modules.replace("，", ",").replace(" ", "");
			String[] ids = modules.split(",");
			if (ids != null && ids.length > 0) {
				for (int i = 0; i < ids.length; i++) {
					Integer j = new Integer(ids[i]);
					if (j > 0) {
						Module module = new Module();
						module.setId(j);
						role.getModules().add(module);
					}
				}
			}
		}
	}
}
