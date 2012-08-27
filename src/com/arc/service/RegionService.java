package com.arc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.RegionDAO;
import com.arc.entity.Region;
import com.arc.util.Pager;

@Service
public class RegionService {

	@Resource
	RegionDAO regionDAO;

	public void save(Region region) {
		regionDAO.save(region);
	}

	public void update(Region region) {
		regionDAO.update(region);
	}

	public void delete(int id) {
		regionDAO.delete(id);
	}

	public List<Region> getRegions(int state) {
		return regionDAO.getRegions(state);
	}

	public List<Region> getRegions() {
		return regionDAO.getRegions();
	}

	public List<Region> findByPager(Pager pager, String state) {
		return regionDAO.findByPager(pager, state);
	}

	public Region find(int id) {
		return regionDAO.find(id);
	}
}
