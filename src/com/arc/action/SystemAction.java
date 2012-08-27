package com.arc.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Role;

/**
 * 网站后台
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemAction extends BaseAction {

	public String index() {
		return "index";
	}

	public String top() {
		return "top";
	}

	public String left() {
		HashSet set = new HashSet();
		List<Role> roles = roleService.findAdminRole(operator.getId());
		if (roles != null && !roles.isEmpty()) {
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				Role role = (Role) it.next();
				set.addAll(moduleService.findModulesByRole(role.getId()));
			}
			if (set != null && !set.isEmpty()) {
				list = moduleService.getMenus(set);
			}
		}
		return "left";
	}

	public String right() {
		return "right";
	}

	public String middle() {
		return "middle";
	}

}
