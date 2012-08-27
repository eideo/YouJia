package com.arc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.RoleDao;
import com.arc.entity.Role;
import com.arc.util.Pager;

@Service
public class RoleService {
	@Resource
	RoleDao roleDao;

	public Role find(Integer id) {
		return roleDao.find(id);
	}

	public void deleteById(Integer id) {
		roleDao.delete(id);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void save(Role role) {
		roleDao.save(role);
	}

	public List<Role> findByPager(Pager pager) {
		return roleDao.findByPager(pager);
	}

	public List<Role> findAdminRole(Integer adminId) {
		return roleDao.findAdminRole(adminId);
	}

	public List<Role> findAll() {
		return roleDao.findRoles();
	}
}
