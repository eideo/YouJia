package com.arc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arc.dao.UserDAO;
import com.arc.dao.VoterHistoryDAO;

@Service
public class VoterHistoryService {
	@Resource
	VoterHistoryDAO voterHistoryDAO;

	@Resource
	UserDAO userDAO;

	public int getVoterHistoryCount(int regionId, int voterId) {
		return voterHistoryDAO.getVoterHistoryCount(regionId, voterId);
	}

	public int getVoterHistoryCount(int voterId) {
		return voterHistoryDAO.getVoterHistoryCount(voterId);
	}

	public void vote(int regionId, int voterId, int userId, int state) {
		voterHistoryDAO.save(regionId, userId, voterId);
		if (state == 1) {
			userDAO.updateRank1(userId);
		} else if (state == 2) {
			userDAO.updateRank2(userId);
		}
	}

	public int getVoteUserId(int voteId, int regionId) {
		return voterHistoryDAO.getVoteUserId(voteId, regionId);
	}
}
