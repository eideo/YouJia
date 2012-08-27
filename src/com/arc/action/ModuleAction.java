package com.arc.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Module;

/**
 * 系统功能模块管理
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ModuleAction extends BaseAction {

	private Module module;

	public String save() {
		if (module.getParentId() != null && (module.getParentId() < 1)) {
			module.setParentId(null);
		}

		if (module.getParentId() == null) {
			module.setLevel(1);
		} else {
			module.setLevel(moduleService.find(module.getParentId()).getLevel() + 1);
		}
		moduleService.save(module);
		return edit();
	}

	public String update() {

		if (module.getParentId() != null && (module.getParentId() < 1)) {
			module.setParentId(null);
		} else {
			if (module.getId().equals(module.getParentId()))
				return edit();
		}

		Integer level = module.getLevel();
		if (module.getParentId() == null) {
			module.setLevel(1);
		} else {
			module.setLevel(moduleService.find(module.getParentId()).getLevel() + 1);
		}
		moduleService.update(module);
		if (!level.equals(module.getLevel())) {
			// 更新module子节点level
			list = moduleService.findByLevel(level);
			updateLevel(list, module.getId(), module.getLevel());
		}
		result = Result.SUCCESS;
		return edit();
	}

	private void updateLevel(List list, Integer parent, int level) {
		for (int i = 0; i < list.size(); i++) {
			Module module = (Module) list.get(i);
			if (module.getParentId() != null
					&& module.getParentId().equals(parent)) {
				module.setLevel(level + 1);
				moduleService.update(module);
				updateLevel(list, module.getId(), level + 1);
			}
		}
	}

	public String delete() {
		moduleService.deleteById(module.getId());
		return edit();
	}

	public String edit() {
		list = moduleService.findAll();
		if (list != null) {
			// 排序 list，把每个结点对应的下级结点相邻，采用循环查找方法
			for (int i = 0; i < list.size(); i++) {
				Integer currentId = ((Module) list.get(i)).getId();
				int count = list.size() - i - 1;
				for (int j = i + 1; count > 0 && j < list.size(); j++) {
					Module m = (Module) list.get(j);
					// 若此结点不是currentId的下层结点，则放到放到list最后
					if (m.getParentId() == null
							|| !m.getParentId().equals(currentId)) {
						list.remove(j);
						list.add(m);
						j--;
					}
					count--;
				}
			}
		}
		return EDIT;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}
