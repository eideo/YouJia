package com.arc.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.Region;
import com.arc.service.RegionService;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction {
	private Region region;
	private String state;

	@Resource
	RegionService regionService;

	public RegionAction() {
		this.region = new Region();
	}

	public String list() {
		list = regionService.findByPager(pager, state);
		if (list.isEmpty())
			result = Result.FAIL;
		return LIST;
	}

	public String edit() {
		region = regionService.find(region.getId());
		return EDIT;
	}

	public String add() {
		return ADD;
	}

	public String update() {
		regionService.update(region);
		return list();
	}

	public String delete() {
		regionService.delete(region.getId());
		return list();
	}

	public String save() {
		regionService.save(region);
		return list();
	}

	public String query() {
		pager.setCurrentPage("1");
		return list();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}
