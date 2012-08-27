package com.arc.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.Module;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Repository
public class ModuleDao {
	private static final String FIND_MODULE = "select * from sys_module where id=?";
	private static final String GET_ALL_MODULES = "select * from sys_module order by level,priority";
	private static final String GET_ALL_CHILDREN = "select * from sys_module where parent=? order by level,priority";
	private static final String FIND_BY_LEVEL = "select * from sys_module where level=? order by level,priority";
	private static final String FIND_ALL_DISPLAY = "select * from sys_module where display=1 and id in (:ids) order by level,priority";
	private static final String SAVE_MODULE = "insert into sys_module(parent,name,url,functions,priority,display,level,remark) values(?,?,?,?,?,?,?,?)";
	private static final String UPDATE_MODULE = "update sys_module set parent=?,name=?,url=?,functions=?,priority=?,display=?,level=?,remark=? where id=?";
	private static final String DELETE_MODULE = "delete from sys_module where id=?";
	private static final String FIND_ROLE_MODULES = "select * from sys_module where id in (select module from sys_role_module where role=?)";
	@Resource
	Dbo dbo;

	public List<Module> findModules() {
		List<DataBean> tmpList = dbo.queryForList(GET_ALL_MODULES);
		List<Module> modules = Lists.newArrayList();

		if (tmpList == null)
			return modules;
		for (DataBean bean : tmpList) {
			modules.add(mapper(bean));
		}
		return modules;
	}

	public Module find(Integer id) {
		DataBean bean = dbo.queryForMapBean(FIND_MODULE, id);
		if (bean == null)
			return null;
		return mapper(bean);
	}

	public List<Module> findChildren(Integer parentId) {
		List<DataBean> tmpList = dbo.queryForList(GET_ALL_CHILDREN, parentId);
		List<Module> modules = Lists.newArrayList();

		if (tmpList == null)
			return modules;
		for (DataBean bean : tmpList) {
			modules.add(mapper(bean));
		}
		return modules;
	}

	public List<Module> findByLevel(Integer level) {
		List<DataBean> tmpList = dbo.queryForList(FIND_BY_LEVEL, level);
		List<Module> modules = Lists.newArrayList();

		if (tmpList == null)
			return modules;
		for (DataBean bean : tmpList) {
			modules.add(mapper(bean));
		}
		return modules;
	}

	public Integer save(Module module) {
		Number id = dbo.insert(SAVE_MODULE, module.getParentId(),
				module.getName(), module.getUrl(), module.getFunctions(),
				module.getPriority(), module.getDisplay(), module.getLevel(),
				module.getRemark());
		if (id == null)
			return null;
		return id.intValue();
	}

	public void update(Module module) {
		dbo.update(UPDATE_MODULE, module.getParentId(), module.getName(),
				module.getUrl(), module.getFunctions(), module.getPriority(),
				module.getDisplay(), module.getLevel(), module.getRemark(),
				module.getId());
	}

	public void delete(Integer id) {
		dbo.update(DELETE_MODULE, id);
	}

	public List<Module> findModulesByRole(Integer roleId) {
		List<DataBean> tmpList = dbo.queryForList(FIND_ROLE_MODULES, roleId);
		List<Module> modules = Lists.newArrayList();

		if (tmpList == null)
			return modules;
		for (DataBean bean : tmpList) {
			modules.add(mapper(bean));
		}
		return modules;
	}

	public List<Module> findDisplayByIds(Set<Integer> ids) {
		List<DataBean> tmpList = dbo.queryForList(FIND_ALL_DISPLAY.replaceAll(
				":ids", Joiner.on(",").join(ids)));
		List<Module> modules = Lists.newArrayList();

		if (tmpList == null)
			return modules;
		for (DataBean bean : tmpList) {
			modules.add(mapper(bean));
		}
		return modules;
	}

	private Module mapper(DataBean bean) {
		Module module = new Module();
		if (bean.containsKey("id")) {
			module.setId(bean.getInt("id"));
		}
		if (bean.containsKey("parent")) {
			module.setParentId(bean.getInt("parent"));
		}
		if (bean.containsKey("name")) {
			module.setName(bean.getString("name"));
		}
		if (bean.containsKey("url")) {
			module.setUrl(bean.getString("url"));
		}
		if (bean.containsKey("functions")) {
			module.setFunctions(bean.getString("functions"));
		}
		if (bean.containsKey("priority")) {
			module.setPriority(bean.getInt("priority"));
		}
		if (bean.containsKey("display")) {
			module.setDisplay(bean.getInt("display"));
		}
		if (bean.containsKey("level")) {
			module.setLevel(bean.getInt("level"));
		}
		if (bean.containsKey("remark")) {
			module.setRemark(bean.getString("remark"));
		}
		return module;
	}
}