package com.arc.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.AdminDao;
import com.arc.dao.ModuleDao;
import com.arc.dao.RoleDao;
import com.arc.entity.Admin;
import com.arc.entity.Module;
import com.arc.entity.Role;
import com.arc.util.Pager;

@Service
public class AdminService {
	@Resource
	RoleDao roleDao;
	@Resource
	AdminDao adminDao;
	@Resource
	ModuleDao moduleDao;

	public Admin check(String username, String password) {
		return adminDao.authenticateAdmin(username, password);
	}

	public Admin find(Integer id) {
		return adminDao.getAdmin(id);
	}

	public Integer save(Admin admin) {
		return adminDao.save(admin);
	}

	public void deleteById(Integer id) {
		adminDao.delete(id);
	}

	public void update(Admin admin) {
		adminDao.update(admin);
	}

	@SuppressWarnings("rawtypes")
	public String getFunctions(Admin admin) {
		String functions = "";
		List<Role> roles = roleDao.findAdminRole(admin.getId());
		if (roles.isEmpty())
			return functions;

		Iterator it = roles.iterator();

		while (it.hasNext()) {
			Role role = (Role) it.next();
			List<Module> modules = moduleDao.findModulesByRole(role.getId());
			if (modules.isEmpty())
				continue;
			Iterator mit = modules.iterator();
			while (mit.hasNext()) {
				Module module = (Module) mit.next();
				functions = functions + ";" + module.getUrl() + ";"
						+ module.getFunctions();
			}

		}
		return functions;
	}

	public List<Admin> findByPager(Pager pager) {
		return adminDao.findByPager(pager);
	}

	public void updateForLogin(Admin admin) {
		adminDao.updateForLogin(admin);
	}

	public List<Admin> findAll() {
		return this.adminDao.getAdmins();
	}

}
