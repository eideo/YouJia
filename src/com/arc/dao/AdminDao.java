package com.arc.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.Admin;
import com.arc.entity.DataBean;
import com.arc.entity.Role;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Repository
public class AdminDao {
	private static final String GET_ADMIN_BY_ID = "select * from sys_admin where id=?";
	private static final String GET_ALL_ADMIN = "select * from sys_admin";
	private static final String SAVE_ADMIN = "insert into sys_admin(username,name,password,sex,email,phone,valid,createtime) values(?,?,?,?,?,?,?,now())";
	private static final String AUTH_ADMIN = "select * from sys_admin where username=? and password=?";
	private static final String DELETE_ADMIN = "delete from sys_admin where id=?";
	private static final String DELETE_ADMIN_ROLE = "delete from sys_admin_role where admin=?";
	private static final String UPDATE_ADMIN = "update sys_admin set username=?,name=?,sex=?,email=?,phone=?,valid=? where id=?";
	private static final String UPDATE_ADMIN_PASSWORD = "update sys_admin set password=? where id=?";
	private static final String UPDATE_FOR_LOGIN = "update sys_admin set lastip=?,lasttime=? where id=?";
	public static final String SAVE_ADMIN_ROLE = "";
	private static final String UPDATE_ADMIN_ALL = "update sys_admin set username=?,password=?,name=?,sex=?,email=?,phone=?,valid=? where id=?";
	private static final String GET_ALL_FOR_PAGER = "select * from sys_admin order by id desc limit ?,?";
	private static final String COUNT = "select count(*) from sys_admin";
	private static final String FIND_ADMIN_ROLE = "select role from sys_admin_role where admin=?";
	@Resource
	Dbo dbo;

	public Admin getAdmin(Integer adminID) {
		DataBean bean = dbo.queryForMapBean(GET_ADMIN_BY_ID, adminID);
		if (bean == null)
			return null;
		Admin admin = mapper(bean);
		setAdminRoles(adminID, admin);
		return admin;
	}

	public List<Admin> getAdmins() {
		List<DataBean> tmpList = dbo.queryForList(GET_ALL_ADMIN);
		List<Admin> admins = Lists.newArrayList();
		if (tmpList == null)
			return admins;
		for (DataBean bean : tmpList) {
			admins.add(mapper(bean));
		}
		return admins;
	}

	/**
	 * 保存管理员信息
	 * 
	 * @param admin
	 * @return
	 */
	public Integer save(final Admin admin) {
		Number id = dbo.insert(SAVE_ADMIN, admin.getUsername(),
				admin.getName(), admin.getPassword(), admin.getSex(),
				admin.getEmail(), admin.getPhone(), admin.getValid());
		if (id == null)
			return null;
		Set<Role> roles = admin.getRoles();
		if (roles.size() == 0)
			return id.intValue();
		updateAdminRole(id.intValue(), roles);
		return id.intValue();
	}

	private void updateAdminRole(Integer id, Set<Role> roles) {
		StringBuilder sql = new StringBuilder(
				"insert into sys_admin_role values ");
		for (Role role : roles) {
			sql.append('(').append(id).append(',').append(role.getId())
					.append("),");
		}
		String strSql = sql.toString();
		dbo.update(strSql.substring(0, sql.length() - 1));
	}

	private void setAdminRoles(Integer id, Admin admin) {
		List<Integer> list = dbo.queryForList(FIND_ADMIN_ROLE, Integer.class,
				id);
		Set<Role> roles = Sets.newHashSet();
		Role role = null;
		for (Integer roleId : list) {
			role = new Role();
			role.setId(roleId);
			roles.add(role);
		}
		admin.setRoles(roles);
	}

	public void delete(Integer id) {
		dbo.update(DELETE_ADMIN, id);
	}

	public void update(Admin admin) {
		if (Strings.isNullOrEmpty(admin.getPassword())) {
			dbo.update(UPDATE_ADMIN, admin.getUsername(), admin.getName(),
					admin.getSex(), admin.getEmail(), admin.getPhone(),
					admin.getValid(), admin.getId());
		} else {
			dbo.update(UPDATE_ADMIN_ALL, admin.getUsername(),
					admin.getPassword(), admin.getName(), admin.getSex(),
					admin.getEmail(), admin.getPhone(), admin.getValid(),
					admin.getId());
		}
		dbo.update(DELETE_ADMIN_ROLE, admin.getId());
		Set<Role> roles = admin.getRoles();
		if (roles.size() == 0)
			return;
		updateAdminRole(admin.getId(), roles);
	}

	/**
	 * 验证管理员帐号
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Admin authenticateAdmin(String username, String password) {
		DataBean bean = dbo.queryForMapBean(AUTH_ADMIN, username, password);
		if (bean == null)
			return null;

		return mapper(bean);
	}

	public void updateForLogin(Admin admin) {
		dbo.update(UPDATE_FOR_LOGIN, admin.getLastIp(), admin.getLastTime(),
				admin.getId());
	}

	public List<Admin> findByPager(Pager pager) {
		int totalSize = dbo.queryForInt(COUNT);
		pager.setTotalSize(totalSize);
		List<DataBean> tmpList = dbo.queryForList(GET_ALL_FOR_PAGER,
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());

		List<Admin> admins = Lists.newArrayList();
		if (tmpList == null)
			return admins;
		for (DataBean bean : tmpList) {
			admins.add(mapper(bean));
		}
		return admins;
	}

	private Admin mapper(DataBean bean) {
		Admin admin = new Admin();
		if (bean.containsKey("id")) {
			admin.setId(bean.getInt("id"));
		}
		if (bean.containsKey("username")) {
			admin.setUsername(bean.getString("username"));
		}
		if (bean.containsKey("name")) {
			admin.setName(bean.getString("name"));
		}
		if (bean.containsKey("password")) {
			admin.setPassword(bean.getString("password"));
		}
		if (bean.containsKey("sex")) {
			admin.setSex(bean.getString("sex"));
		}
		if (bean.containsKey("email")) {
			admin.setEmail(bean.getString("email"));
		}
		if (bean.containsKey("phone")) {
			admin.setPhone(bean.getString("phone"));
		}
		if (bean.containsKey("createTime")) {
			admin.setCreateTime(bean.getDate("createTime"));
		}
		if (bean.containsKey("lastIP")) {
			admin.setLastIp(bean.getString("lastIP"));
		}
		if (bean.containsKey("lastTime")) {
			admin.setLastTime(bean.getDate("lastTime"));
		}
		if (bean.containsKey("valid")) {
			admin.setValid(bean.getString("valid"));
		}
		return admin;
	}

}
