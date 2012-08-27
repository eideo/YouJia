package com.arc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.Region;
import com.arc.util.Pager;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

@Repository
public class RegionDAO {
	@Resource
	private Dbo dbo;

	public void save(Region region) {
		String sql = "insert into regions(name,start_time,end_time,remark,state) values(?,?)";
		dbo.update(sql, region.getName(), region.getStartTime(),
				region.getEndTime(), region.getRemark(), region.getState());
	}

	public void update(Region region) {
		String sql = "update regions set name=?,state=?,start_time=?,end_time=?,remark=? where id=?";
		dbo.update(sql, region.getName(), region.getState(),
				region.getStartTime(), region.getEndTime(), region.getRemark(),
				region.getId());
	}

	public void delete(int id) {
		String sql = "delete from regions where id=?";
		dbo.update(sql, id);
	}

	public List<Region> findByPager(Pager pager, String state) {
		String condition = "";
		if (!Strings.isNullOrEmpty(state)) {
			condition = " where state=" + state;
		}
		int totalSize = dbo.queryForInt("select count(*) from regions"
				+ condition);
		pager.setTotalSize(totalSize);

		List<DataBean> tmpList = dbo.queryForList("select * from regions "
				+ condition + " order by state,id limit ?,?",
				(pager.getCurrentPage() - 1) * pager.getPageSize(),
				pager.getPageSize());
		List<Region> regions = Lists.newArrayList();

		if (tmpList == null)
			return regions;
		for (DataBean bean : tmpList) {
			regions.add(mapper(bean));
		}
		return regions;
	}

	public List<Region> getRegions(int state) {
		String sql = "select * from regions where state=?";
		List<DataBean> tmpList = dbo.queryForList(sql, state);
		List<Region> regions = Lists.newArrayList();
		if (tmpList == null)
			return regions;
		for (DataBean bean : tmpList) {
			regions.add(mapper(bean));
		}
		return regions;
	}

	public List<Region> getRegions() {
		String sql = "select * from regions";
		List<DataBean> tmpList = dbo.queryForList(sql);
		List<Region> regions = Lists.newArrayList();
		if (tmpList == null)
			return regions;
		for (DataBean bean : tmpList) {
			regions.add(mapper(bean));
		}
		return regions;
	}

	private Region mapper(DataBean bean) {
		Region region = new Region();
		if (bean.containsKey("id"))
			region.setId(bean.getInt("id"));
		if (bean.containsKey("name"))
			region.setName(bean.getString("name"));
		if (bean.containsKey("state"))
			region.setState(bean.getInt("state"));
		if (bean.containsKey("start_time"))
			region.setStartTime(bean.getDate("start_time"));
		if (bean.containsKey("end_time"))
			region.setEndTime(bean.getDate("end_time"));
		if (bean.containsKey("remark"))
			region.setRemark(bean.getString("remark"));
		return region;
	}

	public Region find(int id) {
		String sql = "select * from regions where id=?";
		DataBean bean = dbo.queryForMapBean(sql, id);
		if (bean == null)
			return null;
		return mapper(bean);
	}
}
