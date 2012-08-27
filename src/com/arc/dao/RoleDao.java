package com.arc.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.Module;
import com.arc.entity.Role;
import com.arc.util.Pager;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Repository
public class RoleDao {
	private static final String SAVE_ROLE = "insert into sys_role values(?,?,?)";
	private static final String UPDATE_ROLE = "update sys_role set name=?,remark=? where id=?";
	private static final String DELETE_ROLE_MODULE = "delete from sys_role_module where role=?";
	private static final String FIND_ROLE_MODULES = "select module from sys_role_module where role=?";
	private static final String DELETE_ROLE = "delete from sys_role where id=?";
	private static final String FIND_ROLES = "select * from sys_role order by id";
	private static final String FIND_ROLE = "select * from sys_role where id=?";
	private static final String FIND_ROLE_BY_ADMIN = "select * from sys_role where id in (select role from sys_admin_role where admin=?)";
	private static final String GET_ALL_FOR_PAGER = "select * from sys_role order by id desc limit ?,?";
	private static final String COUNT = "select count(*) from sys_role";
	@Resource
	Dbo dbo;

	public Integer save(Role role) {
		Number id = dbo.insert(SAVE_ROLE, 0, role.getName(), role.getRemark());
		if (id == null)
			return null;
		Set<Module> modules = role.getModules();
		if (modules.size() == 0)
			return id.intValue();
		updateRoleModules(id.intValue(), modules);
		return id.intValue();
	}

	public void update(Role role) {
		dbo.update(UPDATE_ROLE, role.getName(), role.getRemark(), role.getId());
		dbo.update(DELETE_ROLE_MODULE, role.getId());
		Set<Module> modules = role.getModules();
		if (modules.size() == 0)
			return;
		updateRoleModules(role.getId(), modules);
	}

	private void updateRoleModules(Integer id, Set<Module> modules) {
		StringBuilder sql = new StringBuilder(
				"insert into sys_role_module values ");
		for (Module module : modules) {
			sql.append('(').append(id).append(',').append(module.getId())
					.append("),");
		}
		String strSql = sql.toString();
		dbo.update(strSql.substring(0, sql.length() - 1));
	}

	public void delete(Integer id) {
		dbo.update(DELETE_ROLE, id);
	}

	public Role find(Integer id) {
		DataBean bean = dbo.queryForMapBean(FIND_ROLE, id);
		if (bean == null)
			return null;
		Role role = mapper(bean);
		setRoleModules(id, role);
		return role;
	}

	private void setRoleModules(Integer id, Role role) {
		List<Integer> list = dbo.queryForList(FIND_ROLE_MODULES, Integer.class,
				id);
		Set<Module> modules = Sets.newHashSet();
		Module module = null;
		for (Integer moduleId : list) {
			module = new Module();
			module.setId(moduleId);
			modules.add(module);
		}
		role.setModules(modules);
	}

	public List<Role> findRoles() {
		List<DataBean> tmpList = dbo.queryForList(FIND_ROLES);
		List<Role> roles = Lists.newArrayList();

		if (tmpList == null)
			return roles;
		for (DataBean bean : tmpList) {
			roles.add(mapper(bean));
		}
		return roles;
	}

	public List<Role> findAdminRole(Integer adminId) {
		List<DataBean> tmpList = dbo.queryForList(FIND_ROLE_BY_ADMIN, adminId);
		List<Role> roles = Lists.newArrayList();

		if (tmpList == null)
			return roles;
		for (DataBean bean : tmpList) {
			roles.add(mapper(bean));
		}
		return roles;
	}

	private Role mapper(DataBean bean) {
		Role role = new Role();
		if (bean.containsKey("id"))
			role.setId(bean.getInt("id"));
		if (bean.containsKey("name"))
			role.setName(bean.getString("name"));
		if (bean.containsKey("remark"))
			role.setRemark(bean.getString("remark"));
		return role;
	}

	public List<Role> findByPager(Pager pager) {
		int totalSize = dbo.queryForInt(COUNT);
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo.queryForList(GET_ALL_FOR_PAGER,
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());
		List<Role> roles = Lists.newArrayList();

		if (tmpList == null)
			return roles;
		for (DataBean bean : tmpList) {
			roles.add(mapper(bean));
		}
		return roles;

	}
}
