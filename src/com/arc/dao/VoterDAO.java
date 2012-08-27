package com.arc.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.arc.entity.DataBean;
import com.arc.entity.Voter;

@Repository
public class VoterDAO {
	@Resource
	Dbo dbo;

	public int save(Voter voter) {
		String sql = "insert into voters(name,phone) values(?,?)";
		return dbo.insert(sql, voter.getName(), voter.getPhone());
	}

	public boolean checkPhone(String phone) {
		String sql = "select count(*) from voters where phone=?";
		return dbo.queryForInt(sql, phone) > 0;
	}

	public Voter getVoter(String name, String phone) {
		String sql = "select * from voters where name=? and phone=?";
		DataBean bean = dbo.queryForMapBean(sql, name, phone);
		if (bean == null)
			return null;
		Voter voter = new Voter();
		voter.setId(bean.getInt("id"));
		voter.setName(bean.getString("name"));
		voter.setPhone(bean.getString("phone"));
		return voter;
	}

	public Voter getVoter(String phone) {
		String sql = "select * from voters where phone=?";
		DataBean bean = dbo.queryForMapBean(sql, phone);
		if (bean == null)
			return null;
		Voter voter = new Voter();
		voter.setId(bean.getInt("id"));
		voter.setName(bean.getString("name"));
		voter.setPhone(bean.getString("phone"));
		return voter;
	}
}
