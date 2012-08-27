package com.arc.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.arc.entity.User;
import com.arc.service.RegionService;
import com.arc.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction {
	private User user;
	private String username;
	private String valid;
	private String regionId;
	private List regions;
	private List pictures;
	@Resource
	UserService userService;
	@Resource
	RegionService regionService;

	public UserAction() {
		this.user = new User();
	}

	public String list() {
		list = userService.findByPager(pager, regionId, username, valid);
		if (list.isEmpty())
			result = Result.FAIL;
		return LIST;
	}

	public String edit() {
		user = userService.find(user.getId());
		regions = regionService.getRegions();
		if (user != null) {
			if (!Strings.isNullOrEmpty(user.getPic2())) {
				pictures = Lists.newArrayList(user.getPic2().split(";"));
			}
		}
		return EDIT;
	}

	public String add() {
		return ADD;
	}

	public String update() {
		userService.update(user);
		return list();
	}

	public String delete() {
		userService.delete(user.getId());
		return list();
	}

	public String save() {
		userService.save(user);
		return list();
	}

	public String query() {
		pager.setCurrentPage("1");
		return list();
	}

	public String view() {
		user = userService.find(user.getId());
		if (user != null) {
			if (!Strings.isNullOrEmpty(user.getPic2())) {
				pictures = Lists.newArrayList(user.getPic2().split(";"));
			}
		}
		return "view";
	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getValid() {
		return valid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public List getRegions() {
		return regions;
	}

	public void setRegions(List regions) {
		this.regions = regions;
	}

	public List getPictures() {
		return pictures;
	}

}
