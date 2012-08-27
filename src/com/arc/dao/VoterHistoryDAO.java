package com.arc.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository
public class VoterHistoryDAO {
	@Resource
	Dbo dbo;

	/**
	 * 海选阶段
	 * 
	 * @param regionId
	 * @param voterId
	 * @return
	 */
	public int getVoterHistoryCount(int regionId, int voterId) {
		String sql = "select count(*) from vote_history where region_id=? and voter_id=? and vote_time = date(now())";
		return dbo.queryForInt(sql, regionId, voterId);
	}

	/**
	 * 决赛阶段
	 * 
	 * @param voterId
	 * @return
	 */
	public int getVoterHistoryCount(int voterId) {
		String sql = "select count(*) from vote_history where region_id=0 and voter_id=? and vote_time = date(now())";
		return dbo.queryForInt(sql, voterId);
	}

	public void save(int regionId, int userId, int voterId) {
		String sql = "insert into vote_history values(?,?,?,now())";
		dbo.update(sql, regionId, userId, voterId);
	}

	public int getVoteUserId(int voteId, int regionId) {
		String sql = "select user_id from vote_history where voter_id=? and region_id=? and vote_time = date(now()) limit 1";
		return dbo.queryForInt(sql, voteId, regionId);
	}
}
