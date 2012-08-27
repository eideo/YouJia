package com.arc.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.ModuleDao;
import com.arc.entity.Module;
import com.arc.util.Order;
import com.google.common.collect.Sets;

@Service
public class ModuleService {

	Order[] orders = new Order[] { Order.asc("level"), Order.asc("priority") };
	@Resource
	ModuleDao moduleDao;

	public List<Module> findAll() {
		return moduleDao.findModules();
	}

	public List<Module> findChildren(Integer parentId) {
		return moduleDao.findChildren(parentId);
	}

	public List<Module> findByLevel(Integer level) {
		return moduleDao.findByLevel(level);
	}

	public Module find(Integer id) {
		return moduleDao.find(id);
	}

	public Integer save(Module module) {
		return moduleDao.save(module);
	}

	public void update(Module module) {
		moduleDao.update(module);
	}

	public void deleteById(Integer id) {
		moduleDao.delete(id);
	}

	public List<Module> findModulesByRole(Integer roleId) {
		return moduleDao.findModulesByRole(roleId);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Module> getMenus(HashSet<Module> set) {
		Set<Integer> ids = Sets.newHashSet();
		if (set != null && !set.isEmpty()) {
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Module module = (Module) it.next();
				ids.add(module.getId());
			}
		}
		return moduleDao.findDisplayByIds(ids);
	}

	public String getCurrentPosition(Integer id) {
		String position = "";
		Module module = moduleDao.find(id);
		if (module != null) {
			position = module.getName();
			while (module != null && module.getParentId() != null
					&& module.getParentId() > 0) {
				module = moduleDao.find(module.getParentId());
				if (module != null) {
					position = module.getName() + " > " + position;
				}
			}
		}
		return position;
	}

}
